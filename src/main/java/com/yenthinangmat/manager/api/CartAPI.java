package com.yenthinangmat.manager.api;

import com.yenthinangmat.manager.entity.CartItem;
import com.yenthinangmat.manager.entity.CartItemCombo;
import com.yenthinangmat.manager.entity.ComboEntity;
import com.yenthinangmat.manager.entity.ProductEntity;
import com.yenthinangmat.manager.service.CartService;
import com.yenthinangmat.manager.service.ComboService;
import com.yenthinangmat.manager.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
public class CartAPI {
    @Autowired
    private CartService cartService;
    @Autowired
    private ProductService productService;

    @Autowired
    private ComboService comboService;
    @GetMapping("/api/cart/product/add/{id}")
    public ResponseEntity<?> addProduct(@PathVariable(name="id") Long id){
        ProductEntity pe= productService.findOneE(id);
        if(pe!=null){
            CartItem cartItem=new CartItem();
            cartItem.setProductId(pe.getId());
            cartItem.setProductName(pe.getProduct_name());
            cartItem.setPrice(pe.getPrice());
            cartItem.setQty(1);
            cartItem.setDiscount((byte)0);
            cartService.addProduct(cartItem);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/api/cart/combo/add/{id}")
    public ResponseEntity<?> addCombo(@PathVariable(name="id")Long id){
        ComboEntity combo=comboService.findOneE(id);
        if(combo!=null){
            CartItemCombo cartItemCombo=new CartItemCombo();
            cartItemCombo.setComboId(combo.getId());
            cartItemCombo.setComboName(combo.getComboName());
            cartItemCombo.setPrice(combo.getPrice());
            cartItemCombo.setQty(1);
            cartItemCombo.setDiscount((byte)0);
            cartService.addCombo(cartItemCombo);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping("/api/cart/show")
    public CartOutPut show(){
        CartOutPut cart=new CartOutPut();
        cart.listCombo=cartService.getAllCombo();
        cart.listProduct=cartService.getAllProduct();
        return cart;
    }
    public class CartOutPut{
        public Collection<CartItemCombo> listCombo;
        public Collection<CartItem> listProduct;
    }
    @GetMapping("/api/cart/product/remove/{id}")
    public ResponseEntity<?> removeProduct(@PathVariable(name="id")Long id){
        cartService.removeProduct(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("/api/cart/combo/remove/{id}")
    public ResponseEntity<?> removeCombo(@PathVariable(name="id")Long id){
        cartService.removeCombo(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("/api/cart/clear")
    public ResponseEntity<?> clear(){
        cartService.clear();
        return new ResponseEntity<>(HttpStatus.OK);

    }
    @GetMapping("/api/cart/save")
    public ResponseEntity<?> save(){
        cartService.saveInvoice();
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PutMapping("/api/cart/product/updateQty/{id}")
    public ResponseEntity<?> updatePQty(@PathVariable(name="id")Long id, @RequestParam int qty){
        cartService.updateProductQty(id,qty);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PutMapping("/api/cart/product/updateD/{id}")
    public ResponseEntity<?> updatePD(@PathVariable(name="id")Long id, @RequestParam byte discount){
        cartService.updatePD(id,discount);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PutMapping("/api/cart/combo/updateQty/{id}")
    public ResponseEntity<?> updateQtyC(@PathVariable(name="id")Long id,@RequestParam int qty){
        cartService.updateComboQty(id,qty);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PutMapping("/api/cart/combo/updateD/{id}")
    public ResponseEntity<?> updateCD(@PathVariable(name="id")Long id,@RequestParam byte discount){
        cartService.updateCD(id,discount);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
