import { RegistrVocabularyApiBody } from 'types/api';
import { instance } from './instance';

// Word
// 국립국어원 단어 조회 (간편 사전 검색)
export const getAllWordApi = async (keyword: string, pageNum?: number) => {
	const response = await instance.get(`/vocabulary-service/api/words?keyword=${keyword}&pageNum=${pageNum ?? 1}`);
	return response;
};

// Vocabulary

// -- 단어장에 등록
export const registVocabularyApi = async (memberKey: string, body: RegistrVocabularyApiBody) => {
	const response = await instance.post(`/vocabulary-service/api/${memberKey}`, body);
	return response;
};

// -- 단어장에 조회
export const getAllVocabularyApi = async (memberKey: string, pageNum?: number, checked?: boolean) => {
	const response = await instance.get(
		`/vocabulary-service/api/${memberKey}?check=${checked ?? false}&pageNum=${pageNum ?? 1}`,
	);
	return response;
};

// -- 단어장에 체크
export const checkVocabularyApi = async (wordId: string) => {
	const response = await instance.patch(`/vocabulary-service/api/${wordId}`);
	return response;
};

// -- 단어장에서 삭제
export const deleteVocabularyApi = async (wordId: string) => {
	const response = await instance.delete(`/vocabulary-service/api/${wordId}`);
	return response;
};

// -- 단어장에서 체크 갯수 조회
export const getCheckVocabularyApi = async (memberKey: string) => {
	const response = await instance.get(`/vocabulary-service/api/${memberKey}/check-count`);
	return response;
};
