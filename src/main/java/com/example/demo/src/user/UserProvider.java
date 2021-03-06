package com.example.demo.src.user;


import com.example.demo.config.BaseException;
import com.example.demo.config.secret.Secret;
import com.example.demo.src.user.model.*;
import com.example.demo.utils.AES128;
import com.example.demo.utils.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.demo.config.BaseResponseStatus.*;

//Provider : Read의 비즈니스 로직 처리
@Service
public class UserProvider {

    private final UserDao userDao;
    private final JwtService jwtService;


    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public UserProvider(UserDao userDao, JwtService jwtService) {
        this.userDao = userDao;
        this.jwtService = jwtService;
    }

    //신청물품 조회
    public List<GetUserProductRes> getUserApply(int userIdx, int page, int limit) throws BaseException{
        try{
            List<GetUserProductRes> getUserProductRes = userDao.getUserApply(userIdx, page, limit);
            return getUserProductRes;
        }
        catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    //등록물품 조회
    public List<GetUserProductRes> getUserRegister(int userIdx, int page, int limit) throws BaseException{
        try{
            List<GetUserProductRes> getUserProductRes = userDao.getUserRegister(userIdx, page, limit);
            return getUserProductRes;
        }
        catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    //회원정보 조회
    public GetUserRes getUser(int userIdx, boolean isMypage) throws BaseException{
        try{
            GetUserRes getUserRes = userDao.getUser(userIdx, isMypage);
            return getUserRes;
        }
        catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    //최근본 물품 조회
    public List<GetProductRes> getUserViewed(int userIdx, int page, int limit) throws BaseException{
        try{
            List<GetProductRes> getProductRes = userDao.getUserViewed(userIdx, page, limit);
            return getProductRes;
        }
        catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    //사용자 주소 조회
    public List<GetAddressRes> getUserAddress(int userIdx) throws BaseException{
        try{
            List<GetAddressRes> getAddressRes = userDao.getUserAddress(userIdx);
            return getAddressRes;
        }
        catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    //사용자 거래내역 조회
    public GetUserRecordRes getUserRecord(int userIdx) throws BaseException{
        try{
            GetUserRecordRes getUserRes = userDao.getUserRecord(userIdx);
            return getUserRes;
        }
        catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }
    //찜한 물품 조회
    public List<GetProductRes> getUserWish(int userIdx, int page, int limit) throws BaseException{
        try{
            List<GetProductRes> getProductRes = userDao.getUserWish(userIdx, page, limit);
            return getProductRes;
        }
        catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public int checkEmail(String email) throws BaseException{
        try{
            return userDao.checkEmail(email);
        } catch (Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public PostLoginRes logIn(PostLoginReq postLoginReq) throws BaseException{
        User user = userDao.getPwd(postLoginReq);
        String password;
        try {
            //password = new AES128(Secret.USER_INFO_PASSWORD_KEY).decrypt(user.getPassword());
        } catch (Exception ignored) {
            throw new BaseException(PASSWORD_DECRYPTION_ERROR);
        }

        /*if(postLoginReq.getPassword().equals(password)){
            int userIdx = userDao.getPwd(postLoginReq).getUserIdx();
            String jwt = jwtService.createJwt(userIdx);
            return new PostLoginRes(userIdx,jwt);
        }
        else{
            throw new BaseException(FAILED_TO_LOGIN);
        }*/
        return null;
    }

}
