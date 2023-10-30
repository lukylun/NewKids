import React from 'react';
import { ReactComponent as Info } from 'assets/icons/infomation.svg';
import { AreaTitleWrapper } from './style';

interface IAreaTitleProps {
	title: string;
	color?: 'Primary' | 'SubFirst' | 'Black';
	subStr?: string;
	hasTooltip?: boolean;
}
function AreaTitle(props: IAreaTitleProps) {
	const { title, color, subStr, hasTooltip } = props;
	return (
		<AreaTitleWrapper $color={color ?? 'Black'}>
			{title}
			{subStr ? (
				<span className="subStr">
					{subStr} <span className="tooltip">{hasTooltip ? <Info /> : <span />}</span>
				</span>
			) : (
				<span />
			)}
		</AreaTitleWrapper>
	);
}

export default AreaTitle;
