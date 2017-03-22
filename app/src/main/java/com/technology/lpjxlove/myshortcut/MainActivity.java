package com.technology.lpjxlove.myshortcut;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private ImageView iv;
    private ShortcutView shortcutView;
    private RecyclerView recyclerView;
    private Bitmap bitmap,b,c;
    private ImageView imageView;
    private static final String ACTION_SHORTCUT = "com.android.launcher.action.INSTALL_SHORTCUT";
    private static final String KEY_DUPLICATE = "duplicate";
    private static final String ACTION_CLEANER_SHORTCUT = "com.gmiles.cleaner.shortcut";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iv= (ImageView) findViewById(R.id.iv);
        imageView= (ImageView) findViewById(R.id.imageView2);
       /* recyclerView= (RecyclerView) findViewById(R.id.recycle_view);
        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(),4));
        recyclerView.setAdapter(new adapter());
*/
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setAnimation();
            }
        });


        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bitmap=convertViewToBitmap(shortcutView);
               /* BitmapDrawable drawable=new BitmapDrawable(getResources(),bitmap);
                b=drawable.getBitmap().copy(Bitmap.Config.ARGB_8888,true);

*/
                String s=  saveToFile();
                b=BitmapFactory.decodeResource(getResources(),R.mipmap.play_);
             //   b=drawable.getBitmap().copy(Bitmap.Config.ARGB_8888,true);
                c=drawBitmap(3);



                iv.setImageBitmap(c);
            }
        });
        shortcutView= (ShortcutView) findViewById(R.id.shortcut);
        shortcutView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createPhoneBoostShortcut(getApplicationContext());
            }
        });

    }



    private void setAnimation(){
        ObjectAnimator animator=ObjectAnimator.ofFloat(imageView,"ScaleX",0f,1f);
        ObjectAnimator animator1=ObjectAnimator.ofFloat(imageView,"ScaleY",0f,1f);
        animator.setDuration(500);
        //animator.start();
        AnimatorSet set=new AnimatorSet();
        set.setDuration(300);
        set.setInterpolator(new AccelerateInterpolator());
        set.playTogether(animator,animator1);
        set.start();



    }




    public Bitmap convertViewToBitmap(View view){

        Bitmap bitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(),
                Bitmap.Config.ARGB_8888);
        //利用bitmap生成画布
        Canvas canvas = new Canvas(bitmap);

        //把view中的内容绘制在画布上
        view.draw(canvas);

        return bitmap;
    }

    public  void createPhoneBoostShortcut(Context context) {
        Intent shortcut = new Intent(ACTION_SHORTCUT);
        shortcut.putExtra(Intent.EXTRA_SHORTCUT_NAME, "heilo");
        shortcut.putExtra(KEY_DUPLICATE, false);//设置是否重复创建
        Intent intent = new Intent(ACTION_CLEANER_SHORTCUT);
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        intent.setClass(context, Main2Activity.class);//设置第一个页面
        shortcut.putExtra(Intent.EXTRA_SHORTCUT_INTENT, intent);

        Intent.ShortcutIconResource iconRes = Intent.ShortcutIconResource.fromContext(context, R.mipmap.ic_launcher);
        shortcut.putExtra(Intent.EXTRA_SHORTCUT_ICON,c);
       // shortcut.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE,iconRes);
        context.sendBroadcast(shortcut);
        Toast.makeText(context, "success", Toast.LENGTH_SHORT).show();
    }




    public String saveToFile(){
        File f=new File(Environment.getExternalStorageDirectory().getPath()+"/123.png");
        try {
            FileOutputStream fos=new FileOutputStream(f);
            bitmap.compress(Bitmap.CompressFormat.PNG,100,fos);
            fos.flush();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Toast.makeText(this, "成功创建！"+f.getPath(), Toast.LENGTH_SHORT).show();
        return f.getPath();
    }




    private Bitmap drawBitmap(int size){
        Paint p=new Paint(Paint.ANTI_ALIAS_FLAG);
        Bitmap a=b.copy(Bitmap.Config.ARGB_8888,true);
        Canvas c=new Canvas(a);

        int height=c.getHeight();
        int width=c.getWidth();


        for(int i=0; i<size ;i++){

            BitmapDrawable bitmapDrawable= (BitmapDrawable) getAppIcon(getPackageName());

            //获取图片，并缩小图片的高宽度大小为canvas的1/3
            Bitmap scaledBitmap=Bitmap
                    .createScaledBitmap(bitmapDrawable.getBitmap(),width/3,height/3,true);

            //设置图片坐标
            float left,top;
            //设置left
            if (i==0||i==2){
                left=width/9;
            }else {
                left=width*5/9;
            }
            //设置top
            if (i==0||i==1){
                top=height/9;
            }else {
                top=height*5/9;
            }
            //绘制图片
            c.drawBitmap(scaledBitmap,left,top,p);
            c.save(Canvas.CLIP_SAVE_FLAG);
            c.restore();
        }
        return a;
    }



    public Drawable getAppIcon(String packname){
        try {
            ApplicationInfo info = getPackageManager().getApplicationInfo(packname, 0);
            return info.loadIcon(getPackageManager());
        } catch (PackageManager.NameNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();

        }
        return null;
    }


/*

    */
/**
     * 更新桌面快捷方式图标，不一定所有图标都有效<br/>
     * 如果快捷方式不存在，则不更新<br/>.
     *//*

    public static void updateShortcutIcon(Context context, String title, Intent intent,Bitmap bitmap) {
        if(bitmap==null){
           // XLog.i(TAG, "update shortcut icon,bitmap empty");
            return;
        }
        try{
            final ContentResolver cr = context.getContentResolver();
            StringBuilder uriStr = new StringBuilder();
            String urlTemp="";
            String authority = LauncherUtil.getAuthorityFromPermissionDefault(context);
            if(authority==null||authority.trim().equals("")){
                authority = LauncherUtil.getAuthorityFromPermission(context,LauncherUtil.getCurrentLauncherPackageName(context)+".permission.READ_SETTINGS");
            }
            uriStr.append("content://");
            if (TextUtils.isEmpty(authority)) {
                int sdkInt = android.os.Build.VERSION.SDK_INT;
                if (sdkInt < 8) { // Android 2.1.x(API 7)以及以下的
                    uriStr.append("com.android.launcher.settings");
                } else if (sdkInt < 19) {// Android 4.4以下
                    uriStr.append("com.android.launcher2.settings");
                } else {// 4.4以及以上
                    uriStr.append("com.android.launcher3.settings");
                }
            } else {
                uriStr.append(authority);
            }
            urlTemp=uriStr.toString();
            uriStr.append("/favorites?notify=true");
            Uri uri = Uri.parse(uriStr.toString());
            Cursor c = cr.query(uri, new String[] {"_id", "title", "intent" },
                    "title=?  and intent=? ",
                    new String[] { title, intent.toUri(0) }, null);
            int index=-1;
            if (c != null && c.getCount() > 0) {
                c.moveToFirst();
                index=c.getInt(0);//获得图标索引
                ContentValues cv=new ContentValues();
                cv.put("icon", flattenBitmap(bitmap));
                Uri uri2=Uri.parse(urlTemp+"/favorites/"+index+"?notify=true");
                int i=context.getContentResolver().update(uri2, cv, null,null);
                context.getContentResolver().notifyChange(uri,null);//此处不能用uri2，是个坑
               // XLog.i(TAG, "update ok: affected "+i+" rows,index is"+index);
            }else{
              //  XLog.i(TAG, "update result failed");
            }
            if (c != null && !c.isClosed()) {
                c.close();
            }
        }catch(Exception ex){
            ex.printStackTrace();
         //   XLog.i(TAG, "update shortcut icon,get errors:"+ex.getMessage());
        }
    }
*/


    private static byte[] flattenBitmap(Bitmap bitmap) {
        // Try go guesstimate how much space the icon will take when serialized
        // to avoid unnecessary allocations/copies during the write.
        int size = bitmap.getWidth() * bitmap.getHeight() * 4;
        ByteArrayOutputStream out = new ByteArrayOutputStream(size);
        try {
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
            out.flush();
            out.close();
            return out.toByteArray();
        } catch (IOException e) {
          //  XLog.w(TAG, "Could not write icon");
            return null;
        }
    }








}
