package com.roknauta.retroRomsDatabase.dataSource.noIntro;

import jakarta.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
public class Serials {

    @XmlAttribute(name = "media_serial1")
    protected String mediaSerial1;
    @XmlAttribute(name = "romchip_serial1")
    protected String romchipSerial1;
    @XmlAttribute(name = "pcb_serial")
    protected String pcbSerial;
    @XmlAttribute(name = "romchip_serial2")
    protected String romchipSerial2;
    @XmlAttribute(name = "mediastamp")
    @XmlSchemaType(name = "unsignedByte")
    protected String mediastamp;
    @XmlAttribute(name = "box_serial")
    protected String boxSerial;
    @XmlAttribute(name = "box_barcode")
    protected String boxBarcode;


    public String getMediaSerial1() {
        return mediaSerial1;
    }


    public void setMediaSerial1(String value) {
        this.mediaSerial1 = value;
    }


    public String getRomchipSerial1() {
        return romchipSerial1;
    }


    public void setRomchipSerial1(String value) {
        this.romchipSerial1 = value;
    }


    public String getPcbSerial() {
        return pcbSerial;
    }


    public void setPcbSerial(String value) {
        this.pcbSerial = value;
    }


    public String getRomchipSerial2() {
        return romchipSerial2;
    }


    public void setRomchipSerial2(String value) {
        this.romchipSerial2 = value;
    }


    public String getMediastamp() {
        return mediastamp;
    }


    public void setMediastamp(String value) {
        this.mediastamp = value;
    }


    public String getBoxSerial() {
        return boxSerial;
    }


    public void setBoxSerial(String value) {
        this.boxSerial = value;
    }


    public String getBoxBarcode() {
        return boxBarcode;
    }


    public void setBoxBarcode(String value) {
        this.boxBarcode = value;
    }

}
