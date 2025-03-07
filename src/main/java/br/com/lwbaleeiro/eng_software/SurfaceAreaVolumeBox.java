package br.com.lwbaleeiro.eng_software;

/***
Write a function that returns the total surface area and volume of a box.
The given input will be three positive non-zero integers: width, height, and depth.
The output should be an array containing two numbers, surface area and volume of the box.
 ***/

public class SurfaceAreaVolumeBox {

    //mine = best
    public static int[] getSize(int w,int h,int d) {
        int surfaceArea = 2 * (w * h + h * d + w * d);
        int volume = w * h * d;
        return new int[] {surfaceArea, volume};
    }
}
