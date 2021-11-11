package vn.dev.danghung.policy;

import org.springframework.stereotype.Component;
import vn.dev.danghung.exception.CommonException;
import vn.dev.danghung.global.ErrorCode;
import vn.dev.danghung.utils.CommonUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class DateTimeRuleImpl extends AbstractRule implements DateTimeRule{
    @Override
    public void verify(String fromDate, String toDate) throws Exception {
        if(CommonUtils.checkEmpty(fromDate)){
            throw new CommonException("From date invalid",ErrorCode.DATE_TIME_INVALID);
        }
        if(CommonUtils.checkEmpty(toDate)){
            throw new CommonException("To date invalid",ErrorCode.DATE_TIME_INVALID);
        }
        try{
            Date day1 = df.parse(fromDate);
            Date day2 = df.parse(toDate);
            Date currentDate = new Date(System.currentTimeMillis());

            if(day1.compareTo(currentDate) > 0){
                throw new CommonException("From date must be before now",ErrorCode.DATE_TIME_INVALID);
            }
            if(day2.compareTo(currentDate) > 0){
                throw new CommonException("To date must be before now",ErrorCode.DATE_TIME_INVALID);
            }
            if (day1.compareTo(day2) > 0 ){
                throw new CommonException("To date must be after from date",ErrorCode.DATE_TIME_INVALID);
            }

        } catch (ParseException e) {
            throw new CommonException("From date or to date parse error",ErrorCode.DATE_TIME_INVALID);
        }
    }

}
