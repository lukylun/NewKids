import React from 'react';
import { Navigate, Outlet } from 'react-router-dom';
import { useRecoilState } from 'recoil';
import { MemberInfoState } from 'store/auth';

function PrivateRoute() {
	const [memberInfoState] = useRecoilState(MemberInfoState);

	return memberInfoState ? <Outlet /> : <Navigate to="/auth/login" />;
}
export default PrivateRoute;
