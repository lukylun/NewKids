import React from 'react';
import { ReactComponent as MyInfo } from 'assets/icons/info.svg';
import { ReactComponent as MyActivity } from 'assets/icons/activity.svg';

export const MYPAGE_MENUS = [
	{ key: 0, name: '내 정보', path: '/mypage/info', icon: <MyInfo /> },
	{ key: 1, name: '활동 내역', path: '/mypage/activity', icon: <MyActivity /> },
];
