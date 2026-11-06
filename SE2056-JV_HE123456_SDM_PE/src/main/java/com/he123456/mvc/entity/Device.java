package com.he123456.mvc.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "devices")
public class Device {
    public Device() {

    }

    public Device(String deviceCode, Integer deviceId, String deviceName, LocalDate installationDate, BigDecimal price, String status, int warrantyMonths) {
        this.deviceCode = deviceCode;
        this.deviceId = deviceId;
        this.deviceName = deviceName;
        this.installationDate = installationDate;
        this.price = price;
        this.status = status;
        this.warrantyMonths = warrantyMonths;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "device_id")
    private Integer deviceId;

    @Column(name = "device_code", columnDefinition = "VARCHAR(6)")
    private String deviceCode;

    @Column(name = "device_name", columnDefinition = "NVARCHAR(200)")
    private String deviceName;

    @Column(columnDefinition = "decimal(10)")
    private BigDecimal price;

    @Column(name = "warranty_months")
    private int warrantyMonths;

    @Column(name = "installation_date", columnDefinition = "DATE")
    private LocalDate installationDate;

    @Column(columnDefinition = "NVARCHAR(10)")
    private String status;

    @ManyToOne(fetch =  FetchType.EAGER)
    @JoinColumn(name = "category_id", referencedColumnName = "category_id")
    private Category category;

    public String getDeviceCode() {
        return deviceCode;
    }

    public void setDeviceCode(String deviceCode) {
        this.deviceCode = deviceCode;
    }

    public Integer getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Integer deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public LocalDate getInstallationDate() {
        return installationDate;
    }

    public void setInstallationDate(LocalDate installationDate) {
        this.installationDate = installationDate;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getWarrantyMonths() {
        return warrantyMonths;
    }

    public void setWarrantyMonths(int warrantyMonths) {
        this.warrantyMonths = warrantyMonths;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
