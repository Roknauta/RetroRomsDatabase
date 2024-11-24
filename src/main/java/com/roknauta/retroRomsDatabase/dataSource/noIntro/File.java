package com.roknauta.retroRomsDatabase.dataSource.noIntro;

import jakarta.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
public class File {

    @XmlAttribute(name = "id", required = true)
    @XmlSchemaType(name = "unsignedShort")
    protected int id;
    @XmlAttribute(name = "extension", required = true)
    protected String extension;
    @XmlAttribute(name = "size", required = true)
    @XmlSchemaType(name = "unsignedInt")
    protected long size;
    @XmlAttribute(name = "crc32", required = true)
    protected String crc32;
    @XmlAttribute(name = "md5", required = true)
    protected String md5;
    @XmlAttribute(name = "sha1", required = true)
    protected String sha1;
    @XmlAttribute(name = "bad")
    @XmlSchemaType(name = "unsignedByte")
    protected Short bad;
    @XmlAttribute(name = "format", required = true)
    protected String format;
    @XmlAttribute(name = "sha256")
    protected String sha256;
    @XmlAttribute(name = "serial")
    protected String serial;


    public int getId() {
        return id;
    }


    public void setId(int value) {
        this.id = value;
    }


    public String getExtension() {
        return extension;
    }


    public void setExtension(String value) {
        this.extension = value;
    }


    public long getSize() {
        return size;
    }


    public void setSize(long value) {
        this.size = value;
    }


    public String getCrc32() {
        return crc32;
    }


    public void setCrc32(String value) {
        this.crc32 = value;
    }


    public String getMd5() {
        return md5;
    }


    public void setMd5(String value) {
        this.md5 = value;
    }


    public String getSha1() {
        return sha1;
    }


    public void setSha1(String value) {
        this.sha1 = value;
    }


    public Short getBad() {
        return bad;
    }


    public void setBad(Short value) {
        this.bad = value;
    }


    public String getFormat() {
        return format;
    }


    public void setFormat(String value) {
        this.format = value;
    }


    public String getSha256() {
        return sha256;
    }


    public void setSha256(String value) {
        this.sha256 = value;
    }


    public String getSerial() {
        return serial;
    }


    public void setSerial(String value) {
        this.serial = value;
    }

}
