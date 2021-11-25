package vn.dev.danghung.adapter;

import vn.dev.danghung.exception.CommonException;

public interface EntitiesAdapter <F,T>{
    T transform(F f) throws CommonException;
}
