import React, { Dispatch, SetStateAction, KeyboardEvent } from 'react';
import { ReactComponent as Search } from 'assets/icons/search.svg';
import { SearchBarContainer } from './style';

interface ISearchBarProps {
	size: 's' | 'l' | 'full';
	value: string;
	setValue: Dispatch<SetStateAction<string>>;
	confirmSearch: () => void;
	placeholder: string;
	color: 'Primary' | 'SubFirst' | 'SubSecond';
}

function SearchBar(props: ISearchBarProps) {
	const { size, confirmSearch, value, setValue, placeholder, color } = props;

	const handleKeydown = (e: KeyboardEvent<HTMLInputElement>) => {
		if (e.key === 'Enter') {
			confirmSearch();
		}
	};
	return (
		<SearchBarContainer $size={size} $color={color}>
			<input
				type="text"
				value={value}
				onChange={(e) => setValue(e.target.value)}
				placeholder={placeholder}
				onKeyDown={handleKeydown}
			/>
			<button type="button" className="confirm-search-btn-wrapper" onClick={confirmSearch}>
				<Search />
			</button>
		</SearchBarContainer>
	);
}

export default SearchBar;
