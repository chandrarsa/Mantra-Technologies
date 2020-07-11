package java2;

import java.math.BigDecimal;

@FunctionalInterface
public interface ReadMetaData {
	BigDecimal getPrice(int position,String row,String delimiter);
}
