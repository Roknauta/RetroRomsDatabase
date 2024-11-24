package com.roknauta.retroRomsDatabase.dataSource.noIntro;

import jakarta.xml.bind.annotation.*;

import javax.xml.datatype.XMLGregorianCalendar;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
public class Details {

    @XmlAttribute(name = "id", required = true)
    @XmlSchemaType(name = "unsignedShort")
    protected int id;
    @XmlAttribute(name = "dirname", required = true)
    protected String dirname;
    @XmlAttribute(name = "rominfo")
    protected String rominfo;
    @XmlAttribute(name = "nfoname", required = true)
    protected String nfoname;
    @XmlAttribute(name = "nfosize", required = true)
    @XmlSchemaType(name = "unsignedShort")
    protected int nfosize;
    @XmlAttribute(name = "nfocrc", required = true)
    protected String nfocrc;
    @XmlAttribute(name = "archivename", required = true)
    protected String archivename;
    @XmlAttribute(name = "originalformat", required = true)
    protected String originalformat;
    @XmlAttribute(name = "date", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar date;
    @XmlAttribute(name = "group", required = true)
    protected String group;
    @XmlAttribute(name = "comment")
    protected String comment;
    @XmlAttribute(name = "region", required = true)
    protected String region;
    @XmlAttribute(name = "origin", required = true)
    protected String origin;
    @XmlAttribute(name = "category")
    protected String category;


    public int getId() {
        return id;
    }


    public void setId(int value) {
        this.id = value;
    }


    public String getDirname() {
        return dirname;
    }


    public void setDirname(String value) {
        this.dirname = value;
    }


    public String getRominfo() {
        return rominfo;
    }


    public void setRominfo(String value) {
        this.rominfo = value;
    }


    public String getNfoname() {
        return nfoname;
    }


    public void setNfoname(String value) {
        this.nfoname = value;
    }


    public int getNfosize() {
        return nfosize;
    }


    public void setNfosize(int value) {
        this.nfosize = value;
    }


    public String getNfocrc() {
        return nfocrc;
    }


    public void setNfocrc(String value) {
        this.nfocrc = value;
    }


    public String getArchivename() {
        return archivename;
    }


    public void setArchivename(String value) {
        this.archivename = value;
    }


    public String getOriginalformat() {
        return originalformat;
    }


    public void setOriginalformat(String value) {
        this.originalformat = value;
    }


    public XMLGregorianCalendar getDate() {
        return date;
    }


    public void setDate(XMLGregorianCalendar value) {
        this.date = value;
    }


    public String getGroup() {
        return group;
    }


    public void setGroup(String value) {
        this.group = value;
    }


    public String getComment() {
        return comment;
    }


    public void setComment(String value) {
        this.comment = value;
    }


    public String getRegion() {
        return region;
    }


    public void setRegion(String value) {
        this.region = value;
    }


    public String getOrigin() {
        return origin;
    }


    public void setOrigin(String value) {
        this.origin = value;
    }


    public String getCategory() {
        return category;
    }


    public void setCategory(String value) {
        this.category = value;
    }

}
