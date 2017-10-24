/**
 * Project Name:Android_Car_Example
 * File Name:Utils.java
 * Package Name:com.amap.api.car.example
 * Date:2015年4月7日下午3:43:05
 */

package com.example.administrator.mysharedumbrella01.utils;

import com.amap.api.maps.AMap;
import com.amap.api.maps.model.BitmapDescriptor;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.example.administrator.mysharedumbrella01.R;

import java.util.ArrayList;


/**
 * ClassName:Utils <br/>  
 * Function: TODO ADD FUNCTION. <br/>  
 * Reason:   TODO ADD REASON. <br/>  
 * Date:     2015年4月7日 下午3:43:05 <br/>  
 * @author yiyi.qi
 * @version
 * @since JDK 1.6
 * @see
 */
public class Utils {

    private static ArrayList<Marker> markers = new ArrayList<Marker>();

    /**
     * 添加模拟测试的车的点
     */
    public static void addEmulateData(AMap amap, LatLng center) {
        if (markers.size() == 0) {
            BitmapDescriptor bitmapDescriptor = BitmapDescriptorFactory
                    .fromResource(R.drawable.stable_cluster_marker_one_normal);

            for (int i = 0; i < 100; i++) {
                double latitudeDelt;
                double longtitudeDelt;
                if(i%2==0) {
                     latitudeDelt = (Math.random() - 0.5) * 0.1;
                     longtitudeDelt = (Math.random() - 0.5) * 0.1;
                }else
                {
                     latitudeDelt = (Math.random() - 0.5) * 0.01;
                     longtitudeDelt = (Math.random() - 0.5) * 0.01;
                }
                MarkerOptions markerOptions = new MarkerOptions();
//                markerOptions.setFlat(true);
//                markerOptions.anchor(0.5f, 0.5f);
                markerOptions.icon(bitmapDescriptor);

                markerOptions.position(new LatLng(center.latitude + latitudeDelt, center.longitude + longtitudeDelt));
                //这里的10跟5 是 可借雨伞 以及 可还雨伞
                markerOptions.snippet("10"+","+"5");
                Marker marker = amap.addMarker(markerOptions);
                markers.add(marker);



            }
        } else {
            for (Marker marker : markers) {
                double latitudeDelt = (Math.random() - 0.5) * 0.1;
                double longtitudeDelt = (Math.random() - 0.5) * 0.1;
                marker.setPosition(new LatLng(center.latitude + latitudeDelt, center.longitude + longtitudeDelt));

            }
        }
    }

    /**
     * 移除marker
     */
    public static void removeMarkers() {
        for (Marker marker : markers) {
            marker.remove();
            marker.destroy();
        }
        markers.clear();
    }

}
  
