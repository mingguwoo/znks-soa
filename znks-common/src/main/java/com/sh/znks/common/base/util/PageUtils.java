package com.sh.znks.common.base.util;

import java.util.List;

/**
 * Created by wuminggu on 2018/6/15.
 */
public class PageUtils {
    /**
     * ʹ��ȫ���������÷�ҳ���������������ӿڵȸ��ӳ�����
     * @param data          �� ȫ������list
     * @param currentPage  �� ��ǰҳ����
     * @param pageSize     �� ÿ��ҳ��ļ�¼��
     * @return  ��ҳҳ��
     */
    public static <T> Page<T> setPageWithAllData(List<T> data, Integer currentPage, Integer pageSize)
    {
        Page<T> page = new Page<T>();
        page.setPage(currentPage);
        page.setPageSize(pageSize);
        page.setTotalRows(data.size());

        // ��ҳ�ĵ�һ�����ݵ�ƫ����(�±�) inclusive
        int fromIndex = (currentPage - 1) * pageSize;
        // ��ҳ�����һ�����ݵ�ƫ����(�±�) exclusive
        int toIndex = currentPage * pageSize;

        // ���һҳ
        if (toIndex  >= page.getTotalRows()) {
            page.setHasNext(false);
            // ���������±�
            toIndex = data.size();
        } else {
            page.setHasNext(true);
        }

        if (currentPage > page.getTotalPage()) {
            page.setContent(null);
        } else {
            page.setContent(data.subList(fromIndex, toIndex));
        }

        return page;
    }

    /**
     * ֱ�����÷�ҳ������������Ѿ�����÷�ҳ�Ľṹ���������ݿ��зֺ�ҳ�Ĳ�ѯ��
     * @param data          �� ��ǰ��ҳ������list
     * @param totalRows    �� �ܵļ�¼��
     * @param currentPage  �� ��ǰҳ����
     * @param pageSize     �� ÿ��ҳ��ļ�¼��
     * @return  ��ҳҳ��
     */
    public static <T> Page<T> setPageWithDirectData(List<T> data, int totalRows, Integer currentPage, Integer pageSize)
    {
        Page<T> page = new Page<T>();
        page.setPage(currentPage);
        page.setPageSize(pageSize);
        page.setTotalRows(totalRows);

        // �����һҳ
        if (currentPage * pageSize  >= page.getTotalRows()) {
            page.setHasNext(false);
            // ���������±�
        } else {
            page.setHasNext(true);
        }

        page.setContent(data);

        return page;
    }


    /**
     * ����һ���յķ�ҳ�������ڲ�ѯ���ݲ�����ʱ���Ĭ�Ϸ��أ�
     * @param currentPage  �� ��ǰҳ����
     * @param pageSize     �� ÿ��ҳ��ļ�¼��
     * @return  ��ҳҳ��
     */
    public static <T> Page<T> setEmptyPage(Integer currentPage, Integer pageSize)
    {
        Page<T> page = new Page<T>();
        page.setPage(currentPage);
        page.setPageSize(pageSize);
        page.setTotalRows(0);
        page.setHasNext(false);
        page.setContent(null);

        return page;
    }
}
