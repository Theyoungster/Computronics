package pl.asie.computronics.util;

import java.security.MessageDigest;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import pl.asie.lib.util.MiscUtils;

public class MiscCUtils {
	public static String getHashForStack(ItemStack stack, boolean includeMetadata) {
		String temp = Item.itemRegistry.getNameForObject(stack.getItem());
		if(includeMetadata) temp += ";" + stack.getItemDamage();
		try {
			byte[] data = MessageDigest.getInstance("MD5").digest(temp.getBytes());
			return MiscUtils.asHexString(data);
		} catch(Exception e) {
			return null;
		}
	}
}
