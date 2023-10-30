import React from 'react';
import Lottie, { Options } from 'react-lottie';
import CompleteAnimation from './lottie.json';

function GameCompleteLottie() {
	const defaultOptions: Options = {
		loop: true,
		autoplay: true,
		animationData: CompleteAnimation,
	};

	return <Lottie options={defaultOptions} width={350} height={350} isClickToPauseDisabled />;
}

export default GameCompleteLottie;
