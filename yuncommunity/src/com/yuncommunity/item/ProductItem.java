package com.yuncommunity.item;

import com.yuncommunity.base.BaseItem;

/**
 * Model class of product.
 * 
 * @author generated by ERMaster
 * @version $Id$
 */
public class ProductItem extends BaseItem {

	/** serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** productId. */
	private Long productid;

	/** informationId. */
	private Long informationid;

	/** name. */
	private String name;

	/** description. */
	private String description;

	/** image. */
	private String image;

	/** time. */
	private String time;

	/**
	 * Constructor.
	 */
	public ProductItem() {
	}

	/**
	 * Set the productId.
	 * 
	 * @param productid
	 *            productId
	 */
	public void setProductid(Long productid) {
		this.productid = productid;
	}

	/**
	 * Get the productId.
	 * 
	 * @return productId
	 */
	public Long getProductid() {
		return this.productid;
	}

	/**
	 * Set the informationId.
	 * 
	 * @param informationid
	 *            informationId
	 */
	public void setInformationid(Long informationid) {
		this.informationid = informationid;
	}

	/**
	 * Get the informationId.
	 * 
	 * @return informationId
	 */
	public Long getInformationid() {
		return this.informationid;
	}

	/**
	 * Set the name.
	 * 
	 * @param name
	 *            name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Get the name.
	 * 
	 * @return name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Set the description.
	 * 
	 * @param description
	 *            description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Get the description.
	 * 
	 * @return description
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * Set the image.
	 * 
	 * @param image
	 *            image
	 */
	public void setImage(String image) {
		this.image = image;
	}

	/**
	 * Get the image.
	 * 
	 * @return image
	 */
	public String getImage() {
		return this.image;
	}

	/**
	 * Set the time.
	 * 
	 * @param time
	 *            time
	 */
	public void setTime(String time) {
		this.time = time;
	}

	/**
	 * Get the time.
	 * 
	 * @return time
	 */
	public String getTime() {
		return this.time;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((productid == null) ? 0 : productid.hashCode());
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		ProductItem other = (ProductItem) obj;
		if (productid == null) {
			if (other.productid != null) {
				return false;
			}
		} else if (!productid.equals(other.productid)) {
			return false;
		}
		return true;
	}

}
