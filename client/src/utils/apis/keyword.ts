import { instance } from './instance';

// 인기 키워드 Top 5 조회
export const getPopularKeywordApi = async () => {
	const response = await instance.get('/keyword-service/api/popular');
	return response;
};

// 뉴스기사 키워드 조회
export const getArticleKeywordApi = async (articleId: string) => {
	const response = await instance.get(`/keyword-service/api/${articleId}/articles`);
	return response;
};

// 나의 키워드 Top 5 조회
export const getMyKeywordApi = async (memberKey: string) => {
	const response = await instance.get(`/keyword-service/api/my/${memberKey}`);
	return response;
};
