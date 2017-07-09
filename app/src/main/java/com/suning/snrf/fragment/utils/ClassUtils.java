package com.suning.snrf.fragment.utils;

import com.suning.snrf.fragment.annotation.sqlite.Id;
import com.suning.snrf.fragment.annotation.sqlite.Table;
import com.suning.snrf.fragment.db.sqlite.ManyToOneLazyLoader;
import com.suning.snrf.fragment.db.table.ManyToOne;
import com.suning.snrf.fragment.db.table.OneToMany;
import com.suning.snrf.fragment.db.table.Property;
import com.suning.snrf.fragment.exception.DbException;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ClassUtils {
	
	
	/**
	 * 鏍规嵁瀹炰綋绫�鑾峰緱 瀹炰綋绫诲搴旂殑琛ㄥ悕
	 * @param entity
	 * @return
	 */
	public static String getTableName(Class<?> clazz) {
		Table table = clazz.getAnnotation(Table.class);
		if(table == null || table.name().trim().length() == 0 ){
			//褰撴病鏈夋敞瑙ｇ殑鏃跺�榛樿鐢ㄧ被鐨勫悕绉颁綔涓鸿〃鍚�骞舵妸鐐癸紙.锛夋浛鎹负涓嬪垝绾�_)
			return clazz.getName().replace('.', '_');
		}
		return table.name();
	}
	
	public static Object getPrimaryKeyValue(Object entity) {
		return FieldUtils.getFieldValue(entity, ClassUtils.getPrimaryKeyField(entity.getClass()));
	}
	
	/**
	 * 鏍规嵁瀹炰綋绫�鑾峰緱 瀹炰綋绫诲搴旂殑琛ㄥ悕
	 * @param entity
	 * @return
	 */
	public static String getPrimaryKeyColumn(Class<?> clazz) {
		String primaryKey = null ;
		Field[] fields = clazz.getDeclaredFields();
		if(fields != null){
			Id idAnnotation = null ;
			Field idField = null ;
			
			for(Field field : fields){ //鑾峰彇ID娉ㄨВ
				idAnnotation = field.getAnnotation(Id.class);
				if(idAnnotation != null){
					idField = field;
					break;
				}
			}
			
			if(idAnnotation != null){ //鏈塈D娉ㄨВ
				primaryKey = idAnnotation.column();
				if(primaryKey == null || primaryKey.trim().length() == 0)
					primaryKey = idField.getName();
			}else{ //娌℃湁ID娉ㄨВ,榛樿鍘绘壘 _id 鍜�id 涓轰富閿紝浼樺厛瀵绘壘 _id
				for(Field field : fields){
					if("_id".equals(field.getName()))
						return "_id";
				}
				
				for(Field field : fields){
					if("id".equals(field.getName()))
						return "id";
				}
			}
		}else{
			throw new RuntimeException("this model["+clazz+"] has no field");
		}
		return primaryKey;
	}
	
	
	/**
	 * 鏍规嵁瀹炰綋绫�鑾峰緱 瀹炰綋绫诲搴旂殑琛ㄥ悕
	 * @param entity
	 * @return
	 */
	public static Field getPrimaryKeyField(Class<?> clazz) {
		Field primaryKeyField = null ;
		Field[] fields = clazz.getDeclaredFields();
		if(fields != null){
			
			for(Field field : fields){ //从子类里面寻找带有@ID的注解
				if(field.getAnnotation(Id.class) != null){
					primaryKeyField = field;
					break;
				}
			}
			
			if(primaryKeyField == null){ //从子类里面寻找_id字段
				for(Field field : fields){
					if("_id".equals(field.getName())){
						primaryKeyField = field;
						break;
					}
				}
			}
			
			if(primaryKeyField == null){ // 从子类里面寻找ID字段
				for(Field field : fields){
					if("id".equals(field.getName())){
						primaryKeyField = field;
						break;
					}
				}
			}
			
			
			if(primaryKeyField == null){ //从父类里面寻找ID字段
				Class<?> parent = clazz.getSuperclass();
				if(parent == null) return primaryKeyField;
				Field[] fps = parent.getDeclaredFields();
				for(Field field : fps){
					if("id".equals(field.getName()) 
							|| "_id".equals(field.getName()) 
							|| field.getAnnotation(Id.class) != null)
					{
						primaryKeyField = field;
						break;
					}
				}
			}
			
		}else{
			throw new RuntimeException("this model["+clazz+"] has no field");
		}
		return primaryKeyField;
	}
	
	
	/**
	 * 鏍规嵁瀹炰綋绫�鑾峰緱 瀹炰綋绫诲搴旂殑琛ㄥ悕
	 * @param entity
	 * @return
	 */
	public static String getPrimaryKeyFieldName(Class<?> clazz) {
		Field f = getPrimaryKeyField(clazz);
		return f==null ? null:f.getName();
	}
	
	
	
	/**
	 * 灏嗗璞¤浆鎹负ContentValues
	 * 
	 * @param entity
	 * @param selective 鏄惁蹇界暐 鍊间负null鐨勫瓧娈�
	 * @return
	 */
	public static List<Property> getPropertyList(Class<?> clazz) {
		
		List<Property> plist = new ArrayList<Property>();
		try {
			Field[] fs = clazz.getDeclaredFields();
			Class<?> parent = clazz.getSuperclass();
			Field[] pfs = parent.getDeclaredFields();
			List<Field> fld = new ArrayList<Field>();
			for(Field ifs : fs){
				fld.add(ifs);
			}
			for(Field ipfs : pfs){
				fld.add(ipfs);
			}
			String primaryKeyFieldName = getPrimaryKeyFieldName(clazz);
			for (Field f : fld) {
				//蹇呴』鏄熀鏈暟鎹被鍨嬪拰娌℃湁鏍囩灛鏃舵�鐨勫瓧娈�
				if(!FieldUtils.isTransient(f)){
					if (FieldUtils.isBaseDateType(f)) {
						
						if(f.getName().equals(primaryKeyFieldName)) //杩囨护涓婚敭
							continue;
						
						Property property = new Property();
					
						property.setColumn(FieldUtils.getColumnByField(f));
						property.setFieldName(f.getName());
						property.setDataType(f.getType());
						property.setDefaultValue(FieldUtils.getPropertyDefaultValue(f));
						property.setSet(FieldUtils.getFieldSetMethod(clazz, f));
						property.setGet(FieldUtils.getFieldGetMethod(clazz, f));
						property.setField(f);
						
						plist.add(property);
					}
				}
			}
			return plist;
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}
	
	
	/**
	 * 灏嗗璞¤浆鎹负ContentValues
	 * 
	 * @param entity
	 * @param selective 鏄惁蹇界暐 鍊间负null鐨勫瓧娈�
	 * @return
	 */
	public static List<ManyToOne> getManyToOneList(Class<?> clazz) {
		
		List<ManyToOne> mList = new ArrayList<ManyToOne>();
		try {
			Field[] fs = clazz.getDeclaredFields();
			for (Field f : fs) {
				if (!FieldUtils.isTransient(f) && FieldUtils.isManyToOne(f)) {
					
					ManyToOne mto = new ManyToOne();
                    //濡傛灉绫诲瀷涓篗anyToOneLazyLoader鍒欏彇绗簩涓弬鏁颁綔涓簃anyClass锛堜竴鏂瑰疄浣擄級 2013-7-26
                    if(f.getType()==ManyToOneLazyLoader.class){
                        Class<?> pClazz = (Class<?>)((ParameterizedType)f.getGenericType()).getActualTypeArguments()[1];
                        if(pClazz!=null)
                            mto.setManyClass(pClazz);
                    }else {
					    mto.setManyClass(f.getType());
                    }
					mto.setColumn(FieldUtils.getColumnByField(f));
					mto.setFieldName(f.getName());
					mto.setDataType(f.getType());	
					mto.setSet(FieldUtils.getFieldSetMethod(clazz, f));
					mto.setGet(FieldUtils.getFieldGetMethod(clazz, f));
					
					mList.add(mto);
				}
			}
			return mList;
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}
	
	
	/**
	 * 灏嗗璞¤浆鎹负ContentValues
	 * 
	 * @param entity
	 * @param selective 鏄惁蹇界暐 鍊间负null鐨勫瓧娈�
	 * @return
	 */
	public static List<OneToMany> getOneToManyList(Class<?> clazz) {
		
		List<OneToMany> oList = new ArrayList<OneToMany>();
		try {
			Field[] fs = clazz.getDeclaredFields();
			for (Field f : fs) {
				if (!FieldUtils.isTransient(f) && FieldUtils.isOneToMany(f)) {
					
					OneToMany otm = new OneToMany();
					
					otm.setColumn(FieldUtils.getColumnByField(f));
					otm.setFieldName(f.getName());
					
					Type type = f.getGenericType();
					
					if(type instanceof ParameterizedType){
						ParameterizedType pType = (ParameterizedType) f.getGenericType();
                        //濡傛灉绫诲瀷鍙傛暟涓�鍒欒涓烘槸LazyLoader 2013-7-25
                        if(pType.getActualTypeArguments().length==1){
						    Class<?> pClazz = (Class<?>)pType.getActualTypeArguments()[0];
						    if(pClazz!=null)
							    otm.setOneClass(pClazz);
                        }else{
                            Class<?> pClazz = (Class<?>)pType.getActualTypeArguments()[1];
                            if(pClazz!=null)
                                otm.setOneClass(pClazz);
                        }
					}else{
						throw new DbException("getOneToManyList Exception:"+f.getName()+"'s type is null");
					}
					/*淇绫诲瀷璧嬪�閿欒鐨刡ug锛宖.getClass杩斿洖鐨勬槸Filed*/
					otm.setDataType(f.getType());
					otm.setSet(FieldUtils.getFieldSetMethod(clazz, f));
					otm.setGet(FieldUtils.getFieldGetMethod(clazz, f));
					
					oList.add(otm);
				}
			}
			return oList;
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}	
	
	
}
