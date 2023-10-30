import React from 'react';
import { PeriodFilterItemWrapper } from './style';

interface IPeriodFilterItemProps {
	filter: IPeriodFilter;
	selectedPeriod: number;
	handleClick: () => void;
}

function PeriodFilterItem(props: IPeriodFilterItemProps) {
	const { filter, selectedPeriod, handleClick } = props;
	return (
		<PeriodFilterItemWrapper $isSelected={selectedPeriod === filter.key} onClick={handleClick}>
			{filter.name}
		</PeriodFilterItemWrapper>
	);
}

export default PeriodFilterItem;
