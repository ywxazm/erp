
package com.redsum.bos.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>waybilldetail complex typeµÄ Java Àà¡£
 * 
 * <p>ÒÔÏÂÄ£Ê½Æ¬¶ÎÖ¸¶¨°üº¬ÔÚ´ËÀàÖÐµÄÔ¤ÆÚÄÚÈÝ¡£
 * 
 * <pre>
 * &lt;complexType name="waybilldetail">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="exedate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="exetime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="info" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sn" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "waybilldetail", propOrder = {
    "exedate",
    "exetime",
    "id",
    "info",
    "sn"
})
public class Waybilldetail {

    protected String exedate;
    protected String exetime;
    protected Integer id;
    protected String info;
    protected Integer sn;

    /**
     * »ñÈ¡exedateÊôÐÔµÄÖµ¡£
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExedate() {
        return exedate;
    }

    /**
     * ÉèÖÃexedateÊôÐÔµÄÖµ¡£
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExedate(String value) {
        this.exedate = value;
    }

    /**
     * »ñÈ¡exetimeÊôÐÔµÄÖµ¡£
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExetime() {
        return exetime;
    }

    /**
     * ÉèÖÃexetimeÊôÐÔµÄÖµ¡£
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExetime(String value) {
        this.exetime = value;
    }

    /**
     * »ñÈ¡idÊôÐÔµÄÖµ¡£
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getId() {
        return id;
    }

    /**
     * ÉèÖÃidÊôÐÔµÄÖµ¡£
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setId(Integer value) {
        this.id = value;
    }

    /**
     * »ñÈ¡infoÊôÐÔµÄÖµ¡£
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInfo() {
        return info;
    }

    /**
     * ÉèÖÃinfoÊôÐÔµÄÖµ¡£
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInfo(String value) {
        this.info = value;
    }

    /**
     * »ñÈ¡snÊôÐÔµÄÖµ¡£
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getSn() {
        return sn;
    }

    /**
     * ÉèÖÃsnÊôÐÔµÄÖµ¡£
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setSn(Integer value) {
        this.sn = value;
    }

}
