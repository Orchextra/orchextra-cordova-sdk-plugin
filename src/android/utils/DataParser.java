package orchextra.utils;

import com.gigigo.orchextra.ORCUser;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import orchextra.entities.OrchextraAuthTokens;
import orchextra.entities.UserMiddleWare;

public class DataParser {

    public OrchextraAuthTokens obtainApiKeyAndSecret(JSONArray args) {
        try {
            OrchextraAuthTokens authTokens = new OrchextraAuthTokens();
            authTokens.setKey(args.getString(0));
            authTokens.setSecret(args.getString(1));
            return authTokens;
        } catch (Exception e) {
            return null;
        }
    }

    public ORCUser obtainUser(JSONArray args) {
        try {
            JSONObject arg = (JSONObject) args.get(0);

            Gson gson = new Gson();
            UserMiddleWare userMiddleWare = gson.fromJson(arg.toString(), UserMiddleWare.class);

            return new ORCUser(userMiddleWare.getCrmId(),
                    getBirthDate(userMiddleWare.getBirthday()),
                    getGender(userMiddleWare.getGender()),
                    new ArrayList<String>());
        } catch (Exception e) {
            return null;
        }
    }

    private GregorianCalendar getBirthDate(String dateInString) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
            Date date = sdf.parse(dateInString);

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);

            return new GregorianCalendar(calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH));
        } catch (Exception e) {
            return null;
        }
    }

    private ORCUser.Gender getGender(String genderInString) {
        if (genderInString.equals("m") || genderInString.equals("M")) {
            return ORCUser.Gender.ORCGenderMale;
        } else if (genderInString.equals("f") || genderInString.equals("F")) {
            return ORCUser.Gender.ORCGenderFemale;
        } else {
            return null;
        }
    }
}
