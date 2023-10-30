import React, { Dispatch, SetStateAction } from 'react';
import { ReactComponent as Check } from 'assets/icons/check.svg';
import { CheckTextButtonWrapper } from './style';

interface ICheckTextButtonProps {
	value: boolean;
	setValue: Dispatch<SetStateAction<boolean>>;
	text: string;
	size: 's' | 'm' | 'l' | 'xl';
}

function CheckTextButton({ value, setValue, text, size }: ICheckTextButtonProps) {
	return (
		<CheckTextButtonWrapper $value={value} $size={size} onClick={() => setValue(!value)}>
			<Check />
			<span>{text}</span>
		</CheckTextButtonWrapper>
	);
}

export default CheckTextButton;
