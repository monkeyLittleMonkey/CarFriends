package com.hbh.cl.carfriends.buycar.model;

/**
 * Created by hbh on 2017/3/22.
 */

public interface IBuyCarModel {
    /**
     * ���������³�����
     * @param callBackListener
     */
    void LoadNewCarData(INewCarCallBackListener callBackListener);

    /**
     * ��������Դ�� ������Ѷ����
     * @param callBackListener
     */
    void LoadECarNewsData(ICarNewsCallBackListener callBackListener);

    /**
     * ��������Դ�� �綯������
     * @param callBackListener
     */
    void LoadECarEData(IECarCallBackListener callBackListener);

    /**
     * ��������Դ�� �춯������
     * @param callBackListener
     */
    void LoadECarHData(IHybridCarCallBackListener callBackListener);

    /**
     * �������ʶ��ֳ�����
     * @param callBackListener
     */
    void loadOldCarData(IOldCarCallBackListener callBackListener);
}
