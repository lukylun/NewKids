import React, { useState, useEffect } from 'react';
import Button from 'components/atoms/common/Button';
import CheckTextButton from 'components/atoms/common/CheckTextButton';
import { AGREE_PERSONAL_INFO, AGREE_TERMS_OF_SERVICES } from 'constants/termsOfService';
import { TermsOfServiceContainer } from './style';

interface ITermsOfServiceProps {
	onNext: () => void;
}

function TermsOfService(props: ITermsOfServiceProps) {
	const [isDone, setIsDone] = useState(false);
	const [agreeFirst, setAgreeFirst] = useState(false);
	const [agreeSecond, setAgreeSecond] = useState(false);
	const { onNext } = props;

	const handleClick = () => {
		if (isDone) {
			onNext();
		} else if (!agreeFirst) {
			// TODO : toast 또는 custom alert으로 교체할 것.
			alert('뉴키즈 이용약관에 동의해주세요.');
		} else if (!agreeSecond) {
			// TODO : toast 또는 custom alert으로 교체할 것.
			alert('개인정보 수집 및 이용에 동의해주세요.');
		} else {
			alert('모든 필수 항목에 동의하세요.');
		}
	};

	const checkAll = () => {
		if (isDone) {
			setAgreeFirst(false);
			setAgreeSecond(false);
		} else {
			setAgreeFirst(true);
			setAgreeSecond(true);
		}
	};

	useEffect(() => {
		if (agreeFirst && agreeSecond) {
			setIsDone(true);
		} else {
			setIsDone(false);
		}
	}, [agreeFirst, agreeSecond]);

	return (
		<TermsOfServiceContainer>
			<div className="agree-form">
				<div className="agree-all agree">
					<CheckTextButton value={isDone} setValue={checkAll} size="l" text="전체 동의하기" />
					<p>모든 항목에 동의합니다.</p>
				</div>
				<div className="agree">
					<CheckTextButton
						setValue={() => setAgreeFirst(!agreeFirst)}
						value={agreeFirst}
						text="[필수] 뉴키즈 이용약관"
						size="m"
					/>
					<textarea readOnly defaultValue={AGREE_TERMS_OF_SERVICES} />
				</div>
				<div className="agree">
					<CheckTextButton
						setValue={() => setAgreeSecond(!agreeSecond)}
						value={agreeSecond}
						text="[필수] 개인정보 수집 및 이용"
						size="m"
					/>
					<textarea readOnly defaultValue={AGREE_PERSONAL_INFO} />
				</div>
			</div>
			<div className="next-btn">
				<Button
					handleClick={handleClick}
					color={isDone ? 'Primary' : 'Normal'}
					size="full"
					radius="m"
					text="다음 단계로"
				/>
			</div>
		</TermsOfServiceContainer>
	);
}

export default TermsOfService;
