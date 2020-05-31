package storage.model.product;

import java.util.Comparator;
/** A comparator needed for sorting
 * @author Tudor
 */
public class ProductBrandComparator implements Comparator<Product> {

    @Override
    public int compare(Product o1, Product o2) {
        return o1.getBrand().compareTo(o2.getBrand());
    }
}
