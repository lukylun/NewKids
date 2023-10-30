/* eslint-disable react/jsx-no-useless-fragment */
import React, { ReactNode, useEffect, useState } from 'react';
import { useRecoilState } from 'recoil';
import { MemberInfoState } from 'store/auth';
import { getMemberInfoApi } from 'utils/apis/auth';

function AuthProvider({ children }: { children: ReactNode }) {
	const [isLoading, setIsLoading] = useState(true);
	const [, setMemberInfoState] = useRecoilState(MemberInfoState);

	const fetchMemberInfoData = async () => {
		const token = localStorage.getItem('token');
		const memberkey = localStorage.getItem('memberkey');

		try {
			if (token && memberkey) {
				const response = await getMemberInfoApi(memberkey);
				if (response.status === 200) {
					setMemberInfoState(response.data.data);
					setIsLoading(false);
				}
			} else {
				setIsLoading(false);
			}
		} catch (error) {
			setIsLoading(false);
			console.log(error);
		}
	};

	useEffect(() => {
		fetchMemberInfoData();
		window.addEventListener('memberLogin', fetchMemberInfoData);

		return () => {
			window.removeEventListener('memberLogin', fetchMemberInfoData);
		};
	}, []);

	if (isLoading) return <div>로딩</div>;
	return <>{children}</>;
}

export default AuthProvider;
