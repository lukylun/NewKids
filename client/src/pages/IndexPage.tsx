import React from 'react';
import AreaTitle from 'components/atoms/common/AreaTitle';
import KeywordCloud from 'components/organisms/article/KeywordCloud';
import PopularArticleList from 'components/organisms/article/PopularArticleList';
import RecommendedArticleList from 'components/organisms/article/RecommendedArticleList';
import TrendingKeywordList from 'components/organisms/article/TrendingKeywordList';
import Footer from 'components/organisms/common/Footer';
import PageLayout from 'layouts/common/PageLayout';
import IndexPageLayout from 'layouts/page/IndexPageLayout';
import moment from 'moment';

function IndexPage() {
	const standardTime = moment(new Date()).format('YY-MM-DD HH:00 업데이트');

	return (
		<PageLayout>
			<IndexPageLayout
				PopularArticleList={
					<>
						<AreaTitle color="Primary" title="지금 인기있는 기사에요!" subStr={standardTime} hasTooltip />
						<PopularArticleList />
					</>
				}
				TrendingKeywordList={
					<>
						<AreaTitle title="떠오르는 키워드에요" subStr={standardTime} hasTooltip />
						<TrendingKeywordList />
					</>
				}
				RecommendedArticleList={
					<>
						<AreaTitle title="내 또래 친구들이 자주봤어요" />
						<RecommendedArticleList />
					</>
				}
				WordCloud={
					<>
						<AreaTitle title="이주의 핫이슈 🔥" subStr={standardTime} hasTooltip />
						<KeywordCloud />
					</>
				}
				Footer={<Footer />}
			/>
		</PageLayout>
	);
}

export default IndexPage;
