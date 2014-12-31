package com.qy.customview.universal_image_loader;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.net.Uri;
import android.os.Environment;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.nostra13.universalimageloader.cache.disc.impl.ext.LruDiscCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

/**
 * 图片工具类
 * 
 * @author 刘宾
 * @date 2014年6月30日 上午10:42:18
 */
public class ImageLoaderUtil {
	private static final String IMAGE_HEAD = "file://";
	private static final String IMAGE_CACHE_DIR = Environment.getExternalStorageDirectory().getPath()+"/QY/images/";

	/** 加载网络url */
	public static void loadWebUrl(String imgUrl, ImageView imageView) {
		ImageLoader.getInstance().displayImage(imgUrl, imageView);
	}
	/** 加载网络url */
	public static void loadWebUrl(String imgUrl, ImageView imageView,ImageLoadingListener listener) {
		ImageLoader.getInstance().displayImage(imgUrl, imageView,listener);
	}

	/** 下载图片 */
	public static void downImage(final String url, final String path,final String filename, final Context context) {
		if (new File(path + filename).exists()) {
//			Toast.makeText(context, "保存成功,路径：" + path + filename, Toast.LENGTH_LONG).show();
			Toast.makeText(context, "该图片已经下载！", Toast.LENGTH_LONG).show();
		} else {
			ImageLoader.getInstance().loadImage(url, new SimpleImageLoadingListener() {
				@Override
				public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
					super.onLoadingComplete(imageUri, view, loadedImage);
					saveBitmap(loadedImage, path, filename);
					scanPhotos(path + filename, context);
					Toast.makeText(context, "保存成功,路径：" + path + filename, Toast.LENGTH_LONG).show();
				}
			});
		}
	}
	public static void scanPhotos(String filePath, Context context) {
		Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
		Uri uri = Uri.fromFile(new File(filePath));
		intent.setData(uri);
		context.sendBroadcast(intent);
	}

	/**
	 * 初始化一个最低的配置
	 */
	public static void initImageLoader_low(Context context){
		System.out.println("ImageUtil --> init low ImageLoader config");
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context).threadPriority(Thread.NORM_PRIORITY - 2)
				.denyCacheImageMultipleSizesInMemory().tasksProcessingOrder(QueueProcessingType.LIFO).threadPoolSize(3)
				.defaultDisplayImageOptions(low_options)
				.memoryCacheSizePercentage(10).writeDebugLogs().build();
		ImageLoader.getInstance().init(config);
	}
	/**
	 * 初始化一个正常的配置
	 */
	public static void initImageLoader_normal(Context context){
		System.out.println("ImageUtil --> init normal ImageLoader config");
		File file = new File(IMAGE_CACHE_DIR);
		if(!file.exists()) file.mkdirs();
		ImageLoaderConfiguration config = null;
        try
        {
	        config = new ImageLoaderConfiguration.Builder(context).threadPriority(Thread.NORM_PRIORITY - 2)
	        		.denyCacheImageMultipleSizesInMemory().tasksProcessingOrder(QueueProcessingType.LIFO).threadPoolSize(5)
	        		.defaultDisplayImageOptions(normal_options)
	        		/*.diskCache(new LruDiscCache(file, new Md5FileNameGenerator(), 0))*/
	        		.memoryCacheSizePercentage(60).writeDebugLogs().build();
        }
        catch (Exception e)
        {
	        e.printStackTrace();
        }
        if(config != null){
        	ImageLoader.getInstance().init(config);
        }
	}
	private final static DisplayImageOptions low_options = new DisplayImageOptions.Builder().bitmapConfig(Bitmap.Config.RGB_565)
//			.showImageOnLoading(R.drawable.bg)    
//		    .showImageForEmptyUri(R.drawable.bg)    
//		    .showImageOnFail(R.drawable.bg)
		    .imageScaleType(ImageScaleType.EXACTLY)
			.cacheOnDisk(false).cacheInMemory(false).build();
	private final static DisplayImageOptions normal_options = new DisplayImageOptions.Builder().bitmapConfig(Bitmap.Config.RGB_565)
//			.showImageOnLoading(R.drawable.bg)    
//			.showImageForEmptyUri(R.drawable.bg)    
//			.showImageOnFail(R.drawable.bg)
			.cacheOnDisk(true).cacheInMemory(true).build();
	/** 加载本地url */
	public static void loadLocalUrl(String imgUrl, ImageView imageView) {
		ImageLoader.getInstance().displayImage(IMAGE_HEAD + imgUrl, imageView, low_options);
	}
	public static void loadLocalUrl(String imgUrl, ImageView imageView,DisplayImageOptions options) {
		ImageLoader.getInstance().displayImage(IMAGE_HEAD + imgUrl, imageView, options);
	}
	/**
	 * 如果需要重新配置ImageLoader，需要调用此方法清理配置
	 */
	public static void destroyImageLoader(){
		try {
			System.out.println("ImageUtil --> destroy ImageLoader config");
			ImageLoader.getInstance().destroy();
		} catch (Exception e) {
			System.err.println("ImageUtil --> destroy ImageLoader config");
			e.printStackTrace();
		}
	}

	/**
	 * 保存位图
	 * 
	 * @param bitmap
	 * @param path
	 * @return
	 */
	public static void saveBitmapForShare(Bitmap bitmap, String path) {
		saveBitmapForShare(bitmap, path, null);
	}

	/**
	 * 保存位图
	 * 
	 * @param bitmap
	 * @param path
	 * @return
	 */
	public static void saveBitmapForShare(Bitmap bitmap, String path, CompressFormat format) {
		if (bitmap == null || TextUtils.isEmpty(path))
			return;
		FileOutputStream fileOut = null;

		try {
			ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
			if (format == null) {
				if (path.endsWith(".png")) {
					format = CompressFormat.PNG;
				} else if (path.endsWith(".jpg") || path.endsWith(".jpeg")) {
					format = CompressFormat.JPEG;
				} else {
					format = CompressFormat.PNG;
				}
			}
			bitmap.compress(format, 100, byteOut);
			byte[] buffer = byteOut.toByteArray();

			fileOut = new FileOutputStream(path);
			fileOut.write(buffer, 0, buffer.length);
			fileOut.flush();
		} catch (Exception ex) {
		} finally {
			try {
				if (fileOut != null)
					fileOut.close();
			} catch (Exception ex) {
			}
		}
	}
	
	/**
	 * 将Bitmap保存为本地图片
	 * 
	 * @param bm
	 * @param path
	 *            路径
	 * @param filename
	 *            文件名
	 * @return
	 */
	public static boolean saveBitmap(Bitmap bm, String path, String filename) {
		File dir = new File(path);
		if (!dir.exists()) {
			dir.mkdir();
		}
		File f = new File(dir, filename);
		try {
			f.createNewFile();
			FileOutputStream out = new FileOutputStream(f);
			bm.compress(Bitmap.CompressFormat.PNG, 90, out);
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
