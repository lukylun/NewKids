import React from 'react';
import ContentLoader from 'react-content-loader';

function FindArticleListItemSkeleton() {
	return (
		<ContentLoader
			speed={2}
			width={1200}
			height={840}
			viewBox="0 0 1200 840"
			backgroundColor="#f3f3f3"
			foregroundColor="#ecebeb"
		>
			<rect x="365" y="119" rx="0" ry="0" width="23" height="1" />
			<rect x="0" y="0" rx="10" ry="10" width="200" height="150" />
			<rect x="218" y="50" rx="10" ry="10" width="250" height="20" />
			<rect x="218" y="17" rx="10" ry="10" width="400" height="24" />
			<rect x="218" y="95" rx="10" ry="10" width="100" height="14" />
			<rect x="218" y="120" rx="10" ry="10" width="150" height="14" />
			<rect x="0" y="168" rx="10" ry="10" width="200" height="150" />
			<rect x="218" y="217" rx="10" ry="10" width="250" height="20" />
			<rect x="218" y="185" rx="10" ry="10" width="400" height="24" />
			<rect x="218" y="262" rx="10" ry="10" width="100" height="14" />
			<rect x="218" y="287" rx="10" ry="10" width="150" height="14" />
			<rect x="218" y="385" rx="10" ry="10" width="250" height="20" />
			<rect x="218" y="353" rx="10" ry="10" width="400" height="24" />
			<rect x="218" y="430" rx="10" ry="10" width="100" height="14" />
			<rect x="218" y="455" rx="10" ry="10" width="150" height="14" />
			<rect x="0" y="336" rx="10" ry="10" width="200" height="150" />
			<rect x="218" y="553" rx="10" ry="10" width="250" height="20" />
			<rect x="218" y="521" rx="10" ry="10" width="400" height="24" />
			<rect x="218" y="598" rx="10" ry="10" width="100" height="14" />
			<rect x="218" y="623" rx="10" ry="10" width="150" height="14" />
			<rect x="0" y="504" rx="10" ry="10" width="200" height="150" />
			<rect x="218" y="721" rx="10" ry="10" width="250" height="20" />
			<rect x="218" y="689" rx="10" ry="10" width="400" height="24" />
			<rect x="218" y="766" rx="10" ry="10" width="100" height="14" />
			<rect x="218" y="791" rx="10" ry="10" width="150" height="14" />
			<rect x="0" y="672" rx="10" ry="10" width="200" height="150" />
		</ContentLoader>
	);
}

export default FindArticleListItemSkeleton;
