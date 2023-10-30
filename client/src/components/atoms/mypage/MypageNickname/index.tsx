import React, { useEffect, useState } from 'react';
import Input from 'components/atoms/common/Input';
import { ReactComponent as NicenameIcon } from 'assets/icons/nickname.svg';
import Button from 'components/atoms/common/Button';
import { PatchNicknameApi, checkNicknameApi } from 'utils/apis/auth';
import { useRecoilState } from 'recoil';
import { MemberInfoState } from 'store/auth';
import { MypageNicknameWrapper } from './style';

function MypageNickname() {
	const [memberInfo] = useRecoilState(MemberInfoState);
	const [nickname, setNickname] = useState('');
	const changeNickname = async () => {
		try {
			const memberKey = localStorage.getItem('memberkey');

			const duplicateRes = await checkNicknameApi({ nickname });
			if (duplicateRes.status === 200 && memberKey !== null) {
				if (duplicateRes.data.data) {
					throw new Error('이미 사용 중인 닉네임입니다.');
				}
				const newNickname = nickname;
				const certNickname = await PatchNicknameApi(memberKey, { newNickname });
				if (certNickname.status === 200) {
					const memberLoginEvent = new Event('memberLogin');
					window.dispatchEvent(memberLoginEvent);
					alert('닉네임 변경이 완료되었습니다.');
				}
			}
		} catch (error) {
			console.log(error);
		}
	};

	useEffect(() => {
		if (memberInfo) {
			setNickname(memberInfo.nickname);
		}
	}, []);
	return (
		<MypageNicknameWrapper>
			<h3>닉네임</h3>
			<div className="nickname-input-content">
				<Input type="text" value={nickname} setValue={setNickname} Icon={<NicenameIcon />} placeholder="" />
				<Button text="변경" radius="l" color="Success" size="s" handleClick={changeNickname} />
			</div>
		</MypageNicknameWrapper>
	);
}

export default MypageNickname;
