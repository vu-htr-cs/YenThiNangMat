package com.yenthinangmat.manager.service;

import com.yenthinangmat.manager.entity.CartItem;
import com.yenthinangmat.manager.entity.CartItemCombo;
import com.yenthinangmat.manager.entity.ReceiptEntity;

import java.util.Collection;

public interface CartService {
    void clear();
    void removeProduct(Long id);
    void removeCombo(Long id);
    void addProduct(CartItem cartItem);
    void addCombo(CartItemCombo cartItemCombo);
    Collection<CartItem> getAllProduct();
    Collection<CartItemCombo> getAllCombo();
    void updateProductQty(Long id,int qty);
    void updateComboQty(Long id,int qty);
    void updatePD(Long id,byte discount);
    void updateCD(Long id,byte discount);
    void saveInvoice();
    int getSubTotal();
    int getCK();
    void fillListDetailReceipt(ReceiptEntity receiptEntity);
}
