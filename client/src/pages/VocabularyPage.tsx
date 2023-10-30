import Pagination from 'components/organisms/common/Pagination';
import MyVocabulary from 'components/organisms/vocabulary/MyVocabulary';
import VocabularyHeader from 'components/organisms/vocabulary/VocabularyHeader';
import PageLayout from 'layouts/common/PageLayout';
import VocabularyLayout from 'layouts/page/VocabularyLayout';
import React, { useEffect, useState } from 'react';
import { IGetallVocaBody } from 'types/vocabulary';
import { getAllVocabularyApi } from 'utils/apis/vocabulary';

function VocabularyPage() {
	const [currentPage, setCurrentPage] = useState(1);
	const [totalPages, setTotalPages] = useState(10);
	const [size, setSize] = useState(10);
	const [currentGroup, setCurrentGroup] = useState(1);
	const [isChecked, setIsChecked] = useState<boolean>(false);
	const [resultVocabularys, setResultVocabularys] = useState<IGetallVocaBody[]>([]);

	const searchPage = async () => {
		try {
			const memberkey = localStorage.getItem('memberkey');

			if (memberkey) {
				const response = await getAllVocabularyApi(memberkey, currentPage, isChecked);
				if (response.status === 200) {
					setResultVocabularys(response.data.data.content);
					setTotalPages(response.data.data.totalPages);
					setSize(response.data.data.size);
				}
			}
		} catch (error) {
			console.log(error);
		}
	};

	useEffect(() => {
		searchPage();
	}, [currentPage, isChecked]);

	useEffect(() => {
		window.addEventListener('reSearchPage', searchPage);
		return () => {
			window.removeEventListener('reSearchPage', searchPage);
		};
	}, []);

	return (
		<PageLayout>
			<VocabularyLayout
				VocabularyHeader={<VocabularyHeader setIsChecked={setIsChecked} currentPage={currentPage} />}
				MyVocabulary={<MyVocabulary vocabularys={resultVocabularys} />}
				Pagination={
					<Pagination
						currentPage={currentPage}
						totalPages={totalPages}
						size={size}
						currentGroup={currentGroup}
						setCurrentPage={setCurrentPage}
						setCurrentGroup={setCurrentGroup}
					/>
				}
			/>
		</PageLayout>
	);
}

export default VocabularyPage;
