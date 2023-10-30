import React, { ReactNode } from 'react';
import { ContentLayout } from 'layouts/common/ContentLayout';
import { VocabularyLayoutContainer } from './style';

interface IVocabularyLayoutProps {
	VocabularyHeader: ReactNode;
	MyVocabulary: ReactNode;
	Pagination: ReactNode;
}

function VocabularyLayout({ VocabularyHeader, MyVocabulary, Pagination }: IVocabularyLayoutProps) {
	return (
		<VocabularyLayoutContainer>
			<div className="voca-header">
				<ContentLayout>{VocabularyHeader} </ContentLayout>
			</div>
			<div className="my-vocabulary">
				<ContentLayout>{MyVocabulary} </ContentLayout>
			</div>
			<div className="pagination">
				<ContentLayout>{Pagination}</ContentLayout>
			</div>
		</VocabularyLayoutContainer>
	);
}

export default VocabularyLayout;
