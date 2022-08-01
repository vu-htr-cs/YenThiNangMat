package com.yenthinangmat.manager.service.impl;

import com.yenthinangmat.manager.dto.PnkAddDTO;
import com.yenthinangmat.manager.entity.*;
import com.yenthinangmat.manager.mapper.PNKMapper;
import com.yenthinangmat.manager.service.*;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@SessionScope
@Service
public class PNKItemServiceImpl implements PNKItemService {
    private final ProductService productService;
    private final PNKService pnkService;
    private final LCServiceImpl lcService;
    final
    ProviderService providerService;
    final
    InventoryService inventoryService;

    Map<Long, PNKItem> mymap = new HashMap<>();

    public PNKItemServiceImpl(ProductService productService, PNKService pnkService, LCServiceImpl lcService, ProviderService providerService, InventoryService inventoryService) {
        this.productService = productService;
        this.pnkService = pnkService;
        this.lcService = lcService;
        this.providerService = providerService;
        this.inventoryService = inventoryService;
    }

    @Override
    public void add(Long productid, int giavon, int soluong) {
        PNKItem cur = mymap.get(productid);
        if (cur == null) {
            PNKItem newElement = new PNKItem();
            ProductEntity product = productService.findOneE(productid);
            newElement.setProductId(productid);
            newElement.setProductName(product.getProduct_name());
            newElement.setUnit(product.getUnitEntity().getName());
            newElement.setGiavon(giavon);
            newElement.setSoluong(soluong);
            mymap.put(productid, newElement);
        } else {
            cur.setSoluong(cur.getSoluong() + soluong);
            cur.setGiavon(cur.getGiavon() + giavon);
        }
    }

    @Override
    public void remove(Long id) {
        mymap.remove(id);
    }

    @Override
    public void clear() {
        mymap.clear();
    }

    @Override
    public Collection<PNKItem> getAllItems() {
        return mymap.values();
    }

    @Override
    public int getCount() {
        if (mymap.isEmpty()) return 0;
        return mymap.values().size();
    }

    @Override
    public int getSum() {
        if (mymap.isEmpty()) return 0;
        else {
            return mymap.values().stream().mapToInt(item -> item.getSoluong() * item.getGiavon()).sum();
        }
    }

    @Override
    @Transactional
    public void save(PnkAddDTO pnkAddDTO) {
        LocationStoreEntity lc = lcService.findOneE(pnkAddDTO.getKhoID());
        ProviderEntity provider = providerService.findOneE(pnkAddDTO.getNccID());
        PNKEntity pnk = PNKMapper.toEntity(pnkAddDTO, lc, provider);

        pnk.setCtpList(getAllItems().stream().map(item -> {
            CtpEntity ctp = new CtpEntity();//tao ctp voi pnk + product id nhu bang trung gian
            ProductEntity pe = productService.findOneE(item.getProductId());
            ctp.setProductCtp(pe);
            ctp.setPnk(pnk);
            ctp.setSoluong(item.getSoluong());
            ctp.setGiaVon(item.getGiavon());

//            InventoryEntity inventoryCur = inventoryService.findOne(item.getProductId());// them vao inventory
//            if (inventoryCur != null && inventoryCur.getGiavon()==item.getGiavon()) {//da co va trung gia von thi tang them so luong
//                inventoryCur.setSoluong(item.getSoluong()+ inventoryCur.getSoluong());
//                inventoryCur.setGiavon(item.getGiavon());
//                inventoryService.saveE(inventoryCur);
//            } else { //khong thi tao cai moi , trung sp nhung khac gia von
//                InventoryEntity inventory = new InventoryEntity();
//                inventory.setInventory_pID(pe);
//                inventory.setSoluong(item.getSoluong());
//                inventory.setGiavon(item.getGiavon());
//                inventoryService.saveE(inventory);
//            }

            List<InventoryEntity> inventoryWrapper=inventoryService.findAllE(item.getProductId());
            boolean inside=false;
            for(InventoryEntity inventoryCur : inventoryWrapper ){
                if(inventoryCur.getGiavon()==item.getGiavon()){//tim thay va gia von nhap = gia von result trong kho
                    inventoryCur.setSoluong(item.getSoluong()+ inventoryCur.getSoluong());
                    inventoryService.saveE(inventoryCur);
                    inside=true;
                    break;
                }
            }
            if(!inside){
                InventoryEntity inventory = new InventoryEntity();
                inventory.setInventory_pID(pe);
                inventory.setSoluong(item.getSoluong());
                inventory.setGiavon(item.getGiavon());
                inventoryService.saveE(inventory);
            }

            return ctp;
        }).collect(Collectors.toList()));
        pnkService.save(pnk);
        mymap.clear();

    }
}
