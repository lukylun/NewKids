export interface IKeyword {
	keywordId: number;
	word: string;
}

export interface IWordCloudKeyword {
	keywordId: number;
	text: string;
	value: number;
}

export interface IWordCloudResponse extends IKeyword {
	totalCount: number;
}

export interface IWord {
	wordKey: number;
	content: string;
	description: string;
}

export interface IMyKeyword {
	keywordId: number;
	keyword: string;
	count: number;
}
