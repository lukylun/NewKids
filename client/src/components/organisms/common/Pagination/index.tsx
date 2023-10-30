import React, { Dispatch, SetStateAction, useEffect, useState } from 'react';
import { PaginationContainer, PaginationItemWrapper } from './style';

function PaginationItem({ page, isActive, handleClick }: { page: number; isActive: boolean; handleClick: () => void }) {
	return (
		<PaginationItemWrapper $isActive={isActive} onClick={handleClick}>
			{page}
		</PaginationItemWrapper>
	);
}

interface IPaginationProps {
	currentPage: number;
	totalPages: number;
	size: number;
	setCurrentPage: Dispatch<SetStateAction<number>>;
	currentGroup: number;
	setCurrentGroup: Dispatch<SetStateAction<number>>;
}

function Pagination(props: IPaginationProps) {
	const { currentPage, totalPages, size, setCurrentPage, currentGroup, setCurrentGroup } = props;
	const [pageNumbers, setPageNumbers] = useState<number[]>([]);

	const scrollTotop = () => {
		window.scrollTo({
			top: 0,
			behavior: 'smooth',
		});
	};

	const setNumbers = () => {
		const newPage = [];
		let endPage = currentGroup * size;

		if (endPage > totalPages) {
			endPage = totalPages;
		}

		for (let i = currentPage; i <= endPage; i += 1) {
			newPage.push(i);
		}

		setPageNumbers(newPage);
	};

	useEffect(() => {
		setNumbers();
	}, [totalPages, currentGroup]);

	return (
		<PaginationContainer>
			{currentGroup !== 1 ? (
				<button
					type="button"
					onClick={() => {
						scrollTotop();
						setCurrentPage((currentGroup - 1) * size - 9);
						setCurrentGroup(currentGroup - 1);
					}}
				>
					{'< 이전'}
				</button>
			) : (
				<div />
			)}

			{pageNumbers.map((el) => (
				<PaginationItem
					page={el}
					key={el}
					isActive={el === currentPage}
					handleClick={() => {
						scrollTotop();
						setCurrentPage(el);
					}}
				/>
			))}

			{totalPages > 10 && currentGroup / size !== totalPages / size ? (
				<button
					type="button"
					onClick={() => {
						scrollTotop();
						setCurrentPage(currentGroup * size + 1);
						setCurrentGroup(currentGroup + 1);
					}}
				>
					{'다음 >'}
				</button>
			) : (
				<div />
			)}
		</PaginationContainer>
	);
}

export default Pagination;
