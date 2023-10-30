import { RegistReadArticleApiBody } from 'types/api';
import { instance } from './instance';

// 기사 전체 조회 (기사 검색)
export const getAllArticleApi = async (startDate: string, endDate: string, content?: string, pageNum?: number) => {
	const url = `/article-service/api?content=${content ?? ''}&pageNum=${
		pageNum ?? 1
	}&startDate=${startDate}&endDate=${endDate}`;

	const response = await instance.get(url);
	return response;
};

// 기사 상세 조회
export const getArticleApi = async (articleId: string) => {
	const response = await instance.get(`/article-service/api/${articleId}`);
	return response;
};

// 메인 추천 기사 조회
export const getAllRecommendedArticleApi = async () => {
	const response = await instance.get(`/recommendation-service/api/main`);
	return response;
};

export const getAllRecommededPeerArticleApi = async () => {
	const response = await instance.get(`/recommendation-service/api/peer-age`);
	return response;
};

export const getAnotherArticleApi = async (articleId: string) => {
	const response = await instance.get(`/recommendation-service/api/another-article?articleId=${articleId}`);
	return response;
};

// TODO :  아래로는 클라이언트에서 필요없어서 삭제 예정
// 기사 삭제
export const deleteArticleApi = async (articleId: number) => {
	const response = await instance.delete(`/articles/api/${articleId}`);
	return response;
};

// 읽은 뉴스기사 등록
export const registReadArticleApi = async (body: RegistReadArticleApiBody, memberKey: string) => {
	const response = await instance.post(`/article-service/api/read/${memberKey}`, body);
	return response;
};

// 읽은 뉴스기사 조회
export const findAllReadArticleApi = async (memberKey: string, pageNum?: number) => {
	let url = `/article-service/api/read/${memberKey}`;
	if (pageNum) {
		url += `?pageNum=${pageNum}`;
	}
	const response = await instance.get(url);
	return response;
};

// 읽은 뉴스기사 조회
export const deleteReadArticleApi = async (articleId: number) => {
	const response = await instance.delete(`/article-service/api/read/${articleId}`);
	return response;
};

// 기사 읽기 (TODO)
export const readArticleApi = async (articleId: number) => {
	const response = await instance.get(`/articles/${articleId}`);
	return response;
};
