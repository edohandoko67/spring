package com.rs.product;

import com.rs.auth.MetaData;
import com.rs.product.satuan.SatuanProduct;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.List;

public class ProductResponseData {
    private MetaData metaData;
    private ProductInfo response;

//    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<SatuanProduct> satuanProducts;
//
//    public List<SatuanProduct> getSatuanProducts() {
//        return satuanProducts;
//    }
//
//    public void setSatuanProducts(List<SatuanProduct> satuanProducts) {
//        this.satuanProducts = satuanProducts;
//    }

    public void ProductResponse() {}

    public ProductResponseData(MetaData metaData, ProductInfo response) {
        this.metaData = metaData;
        this.response = response;
    }

    public MetaData getMetaData() {
        return metaData;
    }

    public void setMetaData(MetaData metaData) {
        this.metaData = metaData;
    }

    public ProductInfo getResponse() {
        return response;
    }

    public void setResponse(ProductInfo response) {
        this.response = response;
    }

}
