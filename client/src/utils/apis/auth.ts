import {
	CertEmailApiBody,
	CheckEmailApiBody,
	CheckNicknameApiBody,
	JoinApiBody,
	LoginApiBody,
	PatchNicknameApiBody,
	PatchPasswordApiBody,
	SendEmailApiBody,
	WithdrawalApiBody,
} from 'types/api';
import { instance } from './instance';

// 로그인
export const loginApi = async (body: LoginApiBody) => {
	const response = await instance.post('/user-service/login', JSON.stringify(body));
	return response;
};

// 회원가입
export const joinApi = async (body: JoinApiBody) => {
	const response = await instance.post('/user-service/join', JSON.stringify(body));
	return response;
};

// 사용자 정보 조회
export const getMemberInfoApi = async (memberKey: string) => {
	const response = await instance.get(`/user-service/api/${memberKey}/info`);
	return response;
};

// 회원 탈퇴
export const withdrawalApi = async (memberKey: string, data: WithdrawalApiBody) => {
	const response = await instance.delete(`/user-service/api/${memberKey}/withdrawal`, { data });
	return response;
};

// 이메일 인증번호 전송
export const sendEmailApi = async (body: SendEmailApiBody) => {
	const response = await instance.post('/user-service/auth/email', JSON.stringify(body));
	return response;
};

// 이메일 인증번호 확인
export const certEmailApi = async (body: CertEmailApiBody) => {
	const response = await instance.post('/user-service/auth/email/check', JSON.stringify(body));
	return response;
};

// 이메일 중복 체크
export const checkEmailApi = async (body: CheckEmailApiBody) => {
	const response = await instance.post('/user-service/auth/duplication/email', JSON.stringify(body));
	return response;
};

// 닉네임 중복 체크
export const checkNicknameApi = async (body: CheckNicknameApiBody) => {
	const response = await instance.post('/user-service/auth/duplication/nickname', JSON.stringify(body));
	return response;
};

// 비밀번호 변경
export const patchPasswordApi = async (memberKey: string, body: PatchPasswordApiBody) => {
	const response = await instance.patch(`/user-service/api/${memberKey}/password`, body);
	return response;
};

// 닉네임 변경
export const PatchNicknameApi = async (memberKey: string, body: PatchNicknameApiBody) => {
	const response = await instance.patch(`/user-service/api/${memberKey}/nickname`, body);
	return response;
};
