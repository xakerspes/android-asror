package aad.myapplication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.hardware.Camera;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import static android.graphics.Color.BLACK;
import static android.graphics.Color.WHITE;

public class MainActivity extends AppCompatActivity {

    SurfaceView sv;
    SurfaceHolder holder;
    HolderCallback holderCallback;
    Camera camera;

    final int CAMERA_ID = 0;
    final boolean FULL_SCREEN = true;
    private ImageView imageView;
    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;
    private Button button5;
    private Button button6;
    private Button button7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //////////////////////////////CODE OF ALISHER//////////////////////////////
        ////////////////////////////////////////////////////////////////////////////


        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        sv = findViewById(R.id.surfaceView);
        holder = sv.getHolder();
//        holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

        holderCallback = new HolderCallback();
        holder.addCallback(holderCallback);

////////////////////////////////////////    END of ALISHER`s code ///////////////////////////////////


        this.imageView = (ImageView) this.findViewById(R.id.imageView);

        this.button1 = (Button) this.findViewById(R.id.button1);
        this.button2 = (Button) this.findViewById(R.id.button2);
        this.button3 = (Button) this.findViewById(R.id.button3);
        this.button4 = (Button) this.findViewById(R.id.button4);
        this.button5 = (Button) this.findViewById(R.id.button5);
        this.button6 = (Button) this.findViewById(R.id.button6);
        this.button7 = (Button) this.findViewById(R.id.button7);

        this.button1.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View v) {
                showImage1();

            }
        });
        this.button2.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View v) {
                showImage2();

            }
        });
        this.button3.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View v) {
                showImage3();

            }
        });
        this.button4.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View v) {
                showImage4();

            }
        });
        this.button5.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View v) {
                showImage5();

            }
        });
        this.button6.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View v) {
                showImage6();

            }
        });
        this.button7.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View v) {
                showImage7();

            }
        });
    }


    //////////////////////////////       CODE OF ALISHER     //////////////////////////////
    ////////////////////////////////////////////////////////////////////////////


    @Override
    protected void onResume() {
        super.onResume();
        camera = Camera.open(CAMERA_ID);
        setPreviewSize(FULL_SCREEN);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (camera != null)
            camera.release();
        camera = null;
    }

    class HolderCallback implements SurfaceHolder.Callback {

        @Override
        public void surfaceCreated(SurfaceHolder holder) {
            try {
                camera.setPreviewDisplay(holder);
                camera.startPreview();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width,
                                   int height) {
            camera.stopPreview();
            setCameraDisplayOrientation(CAMERA_ID);
            try {
                camera.setPreviewDisplay(holder);
                camera.startPreview();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {

        }

    }

    void setPreviewSize(boolean fullScreen) {

        // получаем размеры экрана
        Display display = getWindowManager().getDefaultDisplay();
        boolean widthIsMax = display.getWidth() > display.getHeight();

        // определяем размеры превью камеры
        Camera.Size size = camera.getParameters().getPreviewSize();

        RectF rectDisplay = new RectF();
        RectF rectPreview = new RectF();

        // RectF экрана, соотвествует размерам экрана
        rectDisplay.set(0, 0, display.getWidth(), display.getHeight());

        // RectF первью
        if (widthIsMax) {
            // превью в горизонтальной ориентации
            rectPreview.set(0, 0, size.width, size.height);
        } else {
            // превью в вертикальной ориентации
            rectPreview.set(0, 0, size.height, size.width);
        }

        Matrix matrix = new Matrix();
        // подготовка матрицы преобразования
        if (!fullScreen) {
            // если превью будет "втиснут" в экран (второй вариант из урока)
            matrix.setRectToRect(rectPreview, rectDisplay,
                    Matrix.ScaleToFit.START);
        } else {
            // если экран будет "втиснут" в превью (третий вариант из урока)
            matrix.setRectToRect(rectDisplay, rectPreview,
                    Matrix.ScaleToFit.START);
            matrix.invert(matrix);
        }
        // преобразование
        matrix.mapRect(rectPreview);

        // установка размеров surface из получившегося преобразования
        sv.getLayoutParams().height = (int) (rectPreview.bottom);
        sv.getLayoutParams().width = (int) (rectPreview.right);
    }

    void setCameraDisplayOrientation(int cameraId) {
        // определяем насколько повернут экран от нормального положения
        int rotation = getWindowManager().getDefaultDisplay().getRotation();
        int degrees = 0;
        switch (rotation) {
            case Surface.ROTATION_0:
                degrees = 0;
                break;
            case Surface.ROTATION_90:
                degrees = 90;
                break;
            case Surface.ROTATION_180:
                degrees = 180;
                break;
            case Surface.ROTATION_270:
                degrees = 270;
                break;
        }

        int result = 0;

        // получаем инфо по камере cameraId
        Camera.CameraInfo info = new Camera.CameraInfo();
        Camera.getCameraInfo(cameraId, info);

        // задняя камера
        if (info.facing == Camera.CameraInfo.CAMERA_FACING_BACK) {
            result = ((360 - degrees) + info.orientation);
        } else
            // передняя камера
            if (info.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
                result = ((360 - degrees) - info.orientation);
                result += 360;
            }
        result = result % 360;
        camera.setDisplayOrientation(result);
    }


//////////////////////////////  END of  ALISHER`s code     //////////////////////////////
    ////////////////////////////////////////////////////////////////////////////


    private void showImage1() {

        this.imageView.setImageResource(R.drawable.asd);
    }

    private void showImage2() {

        Bitmap bitmap1 = BitmapFactory.decodeResource(getResources(), R.drawable.asd);
        int mPhotoWidth = bitmap1.getWidth();
        int mPhotoHeight = bitmap1.getHeight();
        Bitmap bitmap = Bitmap.createBitmap(mPhotoWidth, mPhotoHeight,
                Bitmap.Config.ARGB_8888);

        int p = 0;
//
        for (int i = 1; i < mPhotoWidth; i++) {
            for (int j = 1; j < mPhotoHeight; j++) {

                p = bitmap1.getPixel(i, j);

//
                int alpha1 = 255;
                int redValue1 = Color.red(p);
                int greenValue1 = Color.green(p);
                int blueValue1 = Color.blue(p);
                int blur = (int) ((0.299 * redValue1) + (0.587 * greenValue1) + (0.114 * blueValue1));


                bitmap.setPixel(i, j, Color.argb(alpha1, blur, blur, blur));


            }
        }


        this.imageView.setImageBitmap(bitmap);


    }

    private void showImage3() {

        Bitmap bitmap1 = BitmapFactory.decodeResource(getResources(), R.drawable.asd);
        int mPhotoWidth = bitmap1.getWidth();
        int mPhotoHeight = bitmap1.getHeight();
        Bitmap bitmap = Bitmap.createBitmap(mPhotoWidth, mPhotoHeight,
                Bitmap.Config.ARGB_8888);

        int p = 0;
        final int alpha1 = 250;
        for (int i = 1; i < mPhotoWidth; i++) {
            for (int j = 1; j < mPhotoHeight; j++) {

                p = bitmap1.getPixel(i, j);
                int redValue = (int) (Color.red(p));
                int greenValue = (int) (Color.green(p) * 0.5);
                int blueValue = (int) (Color.blue(p) * 0.5);


                if (redValue > 255)
                    redValue = 255;
                if (greenValue > 255)
                    greenValue = 255;
                if (blueValue > 255)
                    blueValue = 255;

//
                bitmap.setPixel(i, j, Color.rgb(redValue, greenValue, blueValue));
//
            }
        }

        this.imageView.setImageBitmap(bitmap);

    }


    private void showImage4() {

        Bitmap bitmap1 = BitmapFactory.decodeResource(getResources(), R.drawable.asd);
        int mPhotoWidth = bitmap1.getWidth();
        int mPhotoHeight = bitmap1.getHeight();
        Bitmap bitmap = Bitmap.createBitmap(mPhotoWidth, mPhotoHeight,
                Bitmap.Config.ARGB_8888);

        int p = 0;
        final int alpha1 = 250;
        for (int i = 1; i < mPhotoWidth; i++) {
            for (int j = 1; j < mPhotoHeight; j++) {

                p = bitmap1.getPixel(i, j);


                int redValue = (int) (Color.red(p) * 0.2126);
                int greenValue = (int) (Color.green(p) * 0.7152);
                int blueValue = (int) (Color.blue(p) * 0.0722);
                int gray = (redValue + greenValue + blueValue);
                bitmap.setPixel(i, j, Color.argb(alpha1, gray, gray, gray));
            }
        }


        this.imageView.setImageBitmap(bitmap);
    }


    private void showImage5() {

        Bitmap bitmap1 = BitmapFactory.decodeResource(getResources(), R.drawable.asd);
        int mPhotoWidth = bitmap1.getWidth();
        int mPhotoHeight = bitmap1.getHeight();
        Bitmap bitmap = Bitmap.createBitmap(mPhotoWidth, mPhotoHeight,
                Bitmap.Config.ARGB_8888);

        int p = 0;
        final int alpha1 = 250;
        for (int i = 1; i < mPhotoWidth; i++) {
            for (int j = 1; j < mPhotoHeight; j++) {

                p = bitmap1.getPixel(i, j);
                int redValue = (Color.red(p));
                int greenValue = (Color.green(p));
                int blueValue = (Color.blue(p));


                if ((redValue + greenValue + blueValue) > 600) {
                    bitmap.setPixel(i, j, BLACK);
                }
                if (((redValue + greenValue + blueValue) < 10) && (redValue + greenValue + blueValue) >= 0) {
                    bitmap.setPixel(i, j, WHITE);
                }
            }
        }

        this.imageView.setImageBitmap(bitmap);
    }


    private void showImage6() {

        Bitmap bitmap1 = BitmapFactory.decodeResource(getResources(), R.drawable.asd);
        int mPhotoWidth = bitmap1.getWidth();
        int mPhotoHeight = bitmap1.getHeight();
        Bitmap bitmap = Bitmap.createBitmap(mPhotoWidth, mPhotoHeight,
                Bitmap.Config.ARGB_8888);

        int p = 0;
        final int alpha1 = 250;
        for (int i = 1; i < mPhotoWidth; i++) {
            for (int j = 1; j < mPhotoHeight; j++) {

                p = bitmap1.getPixel(i, j);
                int redValue = (int) (Color.red(p) * 1.5);
                int greenValue = (int) (Color.green(p) * 1.5);
                int blueValue = (int) (Color.blue(p) * 1.5);


                if (redValue > 255)
                    redValue = 255;
                if (greenValue > 255)
                    greenValue = 255;
                if (blueValue > 255)
                    blueValue = 255;

//                if ((redValue+greenValue+blueValue)>600) {
                bitmap.setPixel(i, j, Color.rgb(redValue, greenValue, blueValue));
//                }
//                if (((redValue+greenValue+blueValue)<10)  && (redValue+greenValue+blueValue)>=0) {
//                    bitmap.setPixel(i, j, WHITE);
//                }
            }
        }

        this.imageView.setImageBitmap(bitmap);

    }

    public int alpha1 = 0;

    @Nullable
    @Override
    public ActionBar getSupportActionBar() {
        return super.getSupportActionBar();
    }

    private void showImage7() {

        Bitmap bitmap1 = BitmapFactory.decodeResource(getResources(), R.drawable.asd);
        int mPhotoWidth = bitmap1.getWidth();
        int mPhotoHeight = bitmap1.getHeight();
        Bitmap bitmap = Bitmap.createBitmap(mPhotoWidth, mPhotoHeight,
                Bitmap.Config.ARGB_8888);

        int p = 0;

        SeekBar seekBar;
        seekBar = (SeekBar) findViewById(R.id.SeekBar);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progressChangedValue = 0;

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progressChangedValue = progress;
            }

            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            public void onStopTrackingTouch(SeekBar seekBar) {
                Toast.makeText(MainActivity.this, "ALPHA =  " + progressChangedValue,
                        Toast.LENGTH_SHORT).show();
                alpha1 = progressChangedValue;
            }
        });


        for (int i = 1; i < mPhotoWidth; i++) {
            for (int j = 1; j < mPhotoHeight; j++) {

                p = bitmap1.getPixel(i, j);


                int redValue1 = Color.red(p);
                int greenValue1 = Color.green(p);
                int blueValue1 = Color.blue(p);
                int blur = (redValue1 + greenValue1 + blueValue1) / 3;
                bitmap.setPixel(i, j, Color.argb(alpha1, blur, blur, blur));
            }
        }

        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "savedBitmap.jpg");

        try {
            FileOutputStream fos = null;
            try {
                fos = new FileOutputStream(file);
                bitmap.compress(Bitmap.CompressFormat.JPEG, 80, fos);
            } finally {
                if (fos != null) fos.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.imageView.setImageBitmap(bitmap);
    }
}