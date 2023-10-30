import React from 'react';
import { KeywordListItemContainer } from './style';

interface IKeywordListItemProps {
	text: string;
	handleClick: () => void;
}

function KeywordListItem(props: IKeywordListItemProps) {
	const { text, handleClick } = props;

	return <KeywordListItemContainer onClick={handleClick}>{text}</KeywordListItemContainer>;
}

export default KeywordListItem;
