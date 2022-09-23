import React, {useEffect} from 'react';

export default function PageTitle(title) {
  useEffect(() => {
    const prevTitle = document.title;
    document.title = title;
    return () => {
      document.title = prevTitle;
    }
  });
}