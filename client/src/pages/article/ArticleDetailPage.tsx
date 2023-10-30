import React, { useEffect, useState } from 'react';
import ArticleContent from 'components/organisms/article/ArticleContent';
import ArticleHeader from 'components/organisms/article/ArticleHeader';
import ArticleKeywordList from 'components/organisms/article/ArticleKeywordList';
import DetailRecommendedArticleList from 'components/organisms/article/DetailRecommendedArticleList';
import Footer from 'components/organisms/common/Footer';
import PageLayout from 'layouts/common/PageLayout';
import ArticleDetailPageLayout from 'layouts/page/ArticleDetailPageLayout';
import { useParams } from 'react-router-dom';
import { IArticle, IArticleDetail } from 'types/article';
import { getAnotherArticleApi, getArticleApi, registReadArticleApi } from 'utils/apis/article';
import { getArticleKeywordApi } from 'utils/apis/keyword';
import { IKeyword } from 'types/keyword';

function ArticleDetailPage() {
	const { articleId } = useParams();
	const [article, setArticle] = useState<IArticleDetail>();
	const [anotherArticles, setAnotherArticles] = useState<IArticle[]>([]);
	const [articleKeywords, setArticleKeywords] = useState<IKeyword[]>([]);

	const fetchKeywordData = async () => {
		try {
			if (articleId) {
				const response = await getArticleKeywordApi(articleId);
				if (response.status === 200) {
					setArticleKeywords(response.data.data);
				}
			}
		} catch (error) {
			console.error(error);
		}
	};

	const fetchArticleData = async () => {
		try {
			if (articleId) {
				const response = await getArticleApi(articleId);
				if (response.status === 200) {
					setArticle(response.data.data);
				}
			}
		} catch (error) {
			console.error(error);
		}
	};

	const fetchAnotherArticleData = async () => {
		try {
			if (articleId) {
				const response = await getAnotherArticleApi(articleId);
				if (response.status === 200) {
					setAnotherArticles(response.data.data);
				}
			}
		} catch (error) {
			console.error(error);
		}
	};

	const registArticleData = async () => {
		try {
			const memberKey = localStorage.getItem('memberkey');

			if (articleId && memberKey) {
				const body = {
					articleId,
				};
				await registReadArticleApi(body, memberKey);
			}
		} catch (error) {
			console.error(error);
		}
	};

	useEffect(() => {
		fetchKeywordData();
		fetchArticleData();
		fetchAnotherArticleData();
		registArticleData();
	}, [articleId]);

	return (
		<PageLayout>
			<ArticleDetailPageLayout
				ArticleHeader={
					<ArticleHeader
						title={article?.title ?? 'title'}
						writer={article?.writer ?? 'writer'}
						publishedDate={article?.publishedDate ?? ''}
						count={article?.count ?? 0}
					/>
				}
				ArticleKeywordList={<ArticleKeywordList keywords={articleKeywords} />}
				ArticleContent={<ArticleContent content={article?.content ?? 'content'} />}
				RecommendedArticleList={<DetailRecommendedArticleList articles={anotherArticles} />}
				Footer={<Footer />}
			/>
		</PageLayout>
	);
}

export default ArticleDetailPage;
