package service;

import config.ApplicationConfig;
import domain.History;
import dto.WifiInfoDto;
import repository.HistoryDao;
import repository.WifiInfoDao;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class SearchWifiInfoService {
    WifiInfoDao wifiInfoDAO;
    HistoryDao historyDAO;
    public ArrayList<WifiInfoDto> getWifiInfoList(double lat, double lnt) {

        wifiInfoDAO = ApplicationConfig.getWifiInfoDao();
        historyDAO = ApplicationConfig.getHistoryDao();

        History history = new History(String.valueOf(lat), String.valueOf(lnt), LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

        boolean result = historyDAO.insert(history);

        if (!result) {
            return null;
        }

        return wifiInfoDAO.getNearWifiInfo(lat, lnt);
    }
}
