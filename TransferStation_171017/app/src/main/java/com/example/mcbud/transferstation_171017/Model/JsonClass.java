package com.example.mcbud.transferstation_171017.Model;

/**
 * Created by mcbud on 2017-10-17.
 */

public class JsonClass {    // Noname 클래스여서 이름이 생성됨
    private StationDayTrnsitNmpr StationDayTrnsitNmpr;

    public StationDayTrnsitNmpr getStationDayTrnsitNmpr ()
    {
        return StationDayTrnsitNmpr;
    }

    public void setStationDayTrnsitNmpr (StationDayTrnsitNmpr StationDayTrnsitNmpr)
    {
        this.StationDayTrnsitNmpr = StationDayTrnsitNmpr;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [StationDayTrnsitNmpr = "+StationDayTrnsitNmpr+"]";
    }
}
