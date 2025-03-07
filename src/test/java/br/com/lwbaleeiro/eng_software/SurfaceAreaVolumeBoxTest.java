package br.com.lwbaleeiro.eng_software;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class SurfaceAreaVolumeBoxTest {


    @Test
    public void Test1() {
        assertArrayEquals(new int[] {88,48},SurfaceAreaVolumeBox.getSize(4, 2, 6));
        assertArrayEquals(new int[]{6,1}, SurfaceAreaVolumeBox.getSize(1, 1, 1));
        assertArrayEquals(new int[]{10,2}, SurfaceAreaVolumeBox.getSize(1, 2, 1));
        assertArrayEquals(new int[]{16,4}, SurfaceAreaVolumeBox.getSize(1, 2, 2));
        assertArrayEquals(new int[]{600,1000}, SurfaceAreaVolumeBox.getSize(10, 10, 10));
    }
}
