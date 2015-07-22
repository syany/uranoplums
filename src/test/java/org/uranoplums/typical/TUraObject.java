/**
 *
 */
package org.uranoplums.typical;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.uranoplums.typical.lang.UraObject;
import org.uranoplums.typical.lang.builder.UraToStringBuilder;

/**
 * @author syany
 *
 */
public class TUraObject extends UraObject {

	protected String testString = "0_String!";
	protected List<String> testList = new ArrayList<String>();
	TSubUraObject tSubUraObject = new TSubUraObject();
	/**
	 *
	 */
	public TUraObject() {
		testList.add("ABC");
		testList.add("TYJ");
	}
//	@Override
//	public String toString() {
//
//		//return new ReflectionToStringBuilder(this)
//	}
	/* (非 Javadoc)
	 * @see org.uranoplums.typical.lang.UraObject#editUraToStringBuilder(org.uranoplums.typical.lang.builder.UraToStringBuilder)
	 */
	@Override
	public UraToStringBuilder editUraToStringBuilder(
			UraToStringBuilder uraToStringBuilder) {
		return super.editUraToStringBuilder(uraToStringBuilder)
				.setIncludeFieldNamesPerttern("List")
				.setIncludeFieldNamesPerttern("Sub")
				.setIncludeFieldNamesPerttern("Map");
	}

}

class TSubUraObject extends UraObject {
	String testString = "1_subString!";
	int hoge = 9;
	Map<String, String> testMap = new TreeMap<String, String>();
	public TSubUraObject() {
		testMap.put("HSP", "VS1");
		testMap.put("KIO", "XYH");
	}
}