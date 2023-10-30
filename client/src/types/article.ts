export interface IArticle {
	articleId: number;
	title: string;
	subTitle: string;
	content: string;
	writer: string;
	publishedDate: string;
	thumbnailImg: string;
}

export interface IArticleDetail {
	title: string;
	subTitle: string;
	writer: string;
	publishedDate: string;
	content: string;
	thumbnailImg: string;
	imageUrls: string[];
	count: number;
}

export interface IMyArticleDetail {
	articleId: number;
	title: string;
	thumbnailImg: string;
}
