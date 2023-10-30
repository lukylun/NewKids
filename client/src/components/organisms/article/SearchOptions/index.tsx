import React, { Dispatch, SetStateAction } from 'react';
import Input from 'components/atoms/common/Input';
import Button from 'components/atoms/common/Button';
import { PERIOD_FILTER_LIST } from 'constants/common';
import PeriodFilterItem from 'components/atoms/article/PeriodFilterItem';
import { SearchOptionsContainer } from './style';

interface ISearchOptionsProps {
	startDate: string;
	setStartDate: Dispatch<SetStateAction<string>>;
	endDate: string;
	setEndDate: Dispatch<SetStateAction<string>>;
	selectedPeriod: number;
	setSelectedPeriod: Dispatch<SetStateAction<number>>;
	search: () => void;
	setCurrentPage: Dispatch<SetStateAction<number>>;
	setCurrentGroup: Dispatch<SetStateAction<number>>;
}

function SearchOptions(props: ISearchOptionsProps) {
	const {
		startDate,
		setStartDate,
		endDate,
		setEndDate,
		selectedPeriod,
		setSelectedPeriod,
		search,
		setCurrentPage,
		setCurrentGroup,
	} = props;

	const onClickSearch = () => {
		setCurrentPage(1);
		setCurrentGroup(1);
		search();
	};
	return (
		<SearchOptionsContainer>
			<div className="wrapper">
				<div className="left">
					<div className="date-input">
						<Input type="date" value={startDate} setValue={setStartDate} />
						<span> ~ </span>
						<Input type="date" value={endDate} setValue={setEndDate} />
					</div>
					<div className="filter">
						{PERIOD_FILTER_LIST.map((el) => (
							<PeriodFilterItem
								key={el.key}
								filter={el}
								selectedPeriod={selectedPeriod}
								handleClick={() => setSelectedPeriod(el.key)}
							/>
						))}
					</div>
				</div>
				<div className="right">
					<Button color="Primary" size="full" text="검색" handleClick={onClickSearch} radius="m" />
				</div>
			</div>
		</SearchOptionsContainer>
	);
}

export default SearchOptions;
