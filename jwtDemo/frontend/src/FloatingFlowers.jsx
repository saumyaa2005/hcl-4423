import React from "react";

// 1. Realistic Illustrated Pink Lotus on Green Lily Leaf (Mid-Left)
const LotusFlower = ({ className }) => (
  <svg viewBox="0 0 160 140" className={className} fill="none">
    <defs>
      <linearGradient id="lilyLeaf" x1="0%" y1="0%" x2="100%" y2="100%">
        <stop offset="0%" stopColor="#4d7c0f" />
        <stop offset="100%" stopColor="#365314" />
      </linearGradient>
      <linearGradient id="lotusPink" x1="0%" y1="100%" x2="0%" y2="0%">
        <stop offset="0%" stopColor="#f472b6" />
        <stop offset="60%" stopColor="#fbcfe8" />
        <stop offset="100%" stopColor="#fdf2f8" />
      </linearGradient>
      <linearGradient id="lotusDeepPink" x1="0%" y1="100%" x2="0%" y2="0%">
        <stop offset="0%" stopColor="#db2777" />
        <stop offset="80%" stopColor="#f472b6" />
        <stop offset="100%" stopColor="#fce7f3" />
      </linearGradient>
      <filter id="lotusShadow" x="-15%" y="-15%" width="130%" height="130%">
        <feDropShadow dx="0" dy="8" stdDeviation="6" floodOpacity="0.12" />
      </filter>
    </defs>

    <g filter="url(#lotusShadow)">
      {/* Green Lily Pad Leaf */}
      <path
        d="M20 95 C15 65 50 45 90 50 C125 55 145 75 140 100 C135 120 100 130 65 125 C35 120 22 110 20 95 Z"
        fill="url(#lilyLeaf)"
      />
      {/* Leaf Cleft */}
      <path d="M78 88 L140 100" stroke="#263e0d" strokeWidth="2" strokeLinecap="round" />

      {/* Outer Lotus Petals */}
      <path d="M42 90 C32 75 42 60 56 68 C68 76 64 92 42 90 Z" fill="url(#lotusDeepPink)" />
      <path d="M112 90 C122 75 112 60 98 68 C86 76 90 92 112 90 Z" fill="url(#lotusDeepPink)" />

      {/* Middle Petals */}
      <path d="M50 88 C40 60 60 40 76 58 C84 68 74 88 50 88 Z" fill="url(#lotusPink)" />
      <path d="M104 88 C114 60 94 40 78 58 C70 68 80 88 104 88 Z" fill="url(#lotusPink)" />

      {/* Central High Petal */}
      <path d="M66 86 C60 50 77 30 88 50 C95 62 86 86 66 86 Z" fill="url(#lotusDeepPink)" opacity="0.95" />
      <path d="M72 85 C68 55 77 35 83 55 C88 66 80 85 72 85 Z" fill="url(#lotusPink)" />

      {/* Yellow Anthers Center */}
      <ellipse cx="77" cy="80" rx="6" ry="3" fill="#facc15" />
    </g>
  </svg>
);

// 2. Realistic Illustrated Coral/Pink Hibiscus with Green Leaves (Bottom-Left)
const HibiscusFlower = ({ className }) => (
  <svg viewBox="0 0 160 150" className={className} fill="none">
    <defs>
      <linearGradient id="hibiscusLeaf" x1="0%" y1="0%" x2="100%" y2="100%">
        <stop offset="0%" stopColor="#65a30d" />
        <stop offset="100%" stopColor="#3f6212" />
      </linearGradient>
      <linearGradient id="hibiscusPetal" x1="0%" y1="100%" x2="0%" y2="0%">
        <stop offset="0%" stopColor="#e11d48" />
        <stop offset="50%" stopColor="#f43f5e" />
        <stop offset="100%" stopColor="#fb7185" />
      </linearGradient>
      <filter id="hibiscusShadow" x="-15%" y="-15%" width="130%" height="130%">
        <feDropShadow dx="0" dy="8" stdDeviation="6" floodOpacity="0.14" />
      </filter>
    </defs>

    <g filter="url(#hibiscusShadow)">
      {/* Background Green Leaves */}
      <path
        d="M20 100 C15 70 45 60 65 75 C60 95 40 110 20 100 Z"
        fill="url(#hibiscusLeaf)"
      />
      <path
        d="M135 110 C145 80 115 70 95 85 C100 105 120 120 135 110 Z"
        fill="url(#hibiscusLeaf)"
      />

      {/* 5 Ruffled Hibiscus Petals */}
      {/* Top Petal */}
      <path
        d="M75 75 C60 35 95 30 85 70 Z"
        fill="url(#hibiscusPetal)"
        stroke="#be123c"
        strokeWidth="1.5"
      />
      {/* Top Left */}
      <path
        d="M75 75 C35 55 50 25 80 65 Z"
        fill="url(#hibiscusPetal)"
        stroke="#be123c"
        strokeWidth="1.5"
      />
      {/* Top Right */}
      <path
        d="M75 75 C115 55 100 25 70 65 Z"
        fill="url(#hibiscusPetal)"
        stroke="#be123c"
        strokeWidth="1.5"
      />
      {/* Bottom Left */}
      <path
        d="M75 75 C40 95 45 125 75 90 Z"
        fill="url(#hibiscusPetal)"
        stroke="#be123c"
        strokeWidth="1.5"
      />
      {/* Bottom Right */}
      <path
        d="M75 75 C110 95 105 125 75 90 Z"
        fill="url(#hibiscusPetal)"
        stroke="#be123c"
        strokeWidth="1.5"
      />

      {/* Center Dark Core */}
      <circle cx="75" cy="73" r="10" fill="#881337" />

      {/* Curved Stamen with Golden Pollen */}
      <path
        d="M75 73 Q85 55 95 45"
        stroke="#f43f5e"
        strokeWidth="3.5"
        strokeLinecap="round"
      />
      <circle cx="95" cy="45" r="3.5" fill="#facc15" />
      <circle cx="91" cy="48" r="2.5" fill="#facc15" />
      <circle cx="87" cy="53" r="2.5" fill="#facc15" />
    </g>
  </svg>
);

// 3. Realistic Illustrated Golden Sunflower with Stem/Leaf (Mid-Right)
const Sunflower = ({ className }) => (
  <svg viewBox="0 0 160 160" className={className} fill="none">
    <defs>
      <linearGradient id="sunflowerPetal" x1="0%" y1="0%" x2="100%" y2="100%">
        <stop offset="0%" stopColor="#fef08a" />
        <stop offset="60%" stopColor="#facc15" />
        <stop offset="100%" stopColor="#eab308" />
      </linearGradient>
      <linearGradient id="sunCenter" x1="0%" y1="0%" x2="100%" y2="100%">
        <stop offset="0%" stopColor="#78350f" />
        <stop offset="60%" stopColor="#451a03" />
        <stop offset="100%" stopColor="#291102" />
      </linearGradient>
      <linearGradient id="sunLeaf" x1="0%" y1="0%" x2="100%" y2="100%">
        <stop offset="0%" stopColor="#4d7c0f" />
        <stop offset="100%" stopColor="#365314" />
      </linearGradient>
      <filter id="sunShadow" x="-15%" y="-15%" width="130%" height="130%">
        <feDropShadow dx="0" dy="8" stdDeviation="6" floodOpacity="0.14" />
      </filter>
    </defs>

    <g filter="url(#sunShadow)">
      {/* Green Stem and Leaf */}
      <path
        d="M80 80 Q90 120 100 145"
        stroke="#4d7c0f"
        strokeWidth="6"
        strokeLinecap="round"
      />
      <path
        d="M92 110 C120 105 135 125 125 140 C105 145 92 130 92 110 Z"
        fill="url(#sunLeaf)"
      />

      {/* Radiating Sunflower Petals */}
      {Array.from({ length: 16 }).map((_, i) => {
        const angle = i * (360 / 16);
        return (
          <ellipse
            key={i}
            cx="80"
            cy="42"
            rx="8"
            ry="24"
            fill="url(#sunflowerPetal)"
            stroke="#ca8a04"
            strokeWidth="0.8"
            transform={`rotate(${angle} 80 80)`}
          />
        );
      })}

      {/* Inner Petal Ring for Density */}
      {Array.from({ length: 16 }).map((_, i) => {
        const angle = i * (360 / 16) + 11.25;
        return (
          <ellipse
            key={`inner-${i}`}
            cx="80"
            cy="48"
            rx="6.5"
            ry="18"
            fill="url(#sunflowerPetal)"
            transform={`rotate(${angle} 80 80)`}
          />
        );
      })}

      {/* Center Seed Disk */}
      <circle cx="80" cy="80" r="22" fill="url(#sunCenter)" />
      <circle cx="80" cy="80" r="18" fill="#451a03" stroke="#92400e" strokeWidth="2" strokeDasharray="3 3" />
    </g>
  </svg>
);

// 4. Realistic Illustrated Yellow Blossom / Daisy (Bottom-Right)
const YellowBlossom = ({ className }) => (
  <svg viewBox="0 0 140 140" className={className} fill="none">
    <defs>
      <linearGradient id="daisyPetal" x1="0%" y1="0%" x2="0%" y2="100%">
        <stop offset="0%" stopColor="#fef9c3" />
        <stop offset="50%" stopColor="#fde047" />
        <stop offset="100%" stopColor="#eab308" />
      </linearGradient>
      <filter id="daisyShadow" x="-15%" y="-15%" width="130%" height="130%">
        <feDropShadow dx="0" dy="6" stdDeviation="5" floodOpacity="0.12" />
      </filter>
    </defs>

    <g filter="url(#daisyShadow)">
      {/* 12 Radiant Yellow Petals */}
      {Array.from({ length: 12 }).map((_, i) => {
        const angle = i * (360 / 12);
        return (
          <ellipse
            key={i}
            cx="70"
            cy="36"
            rx="10"
            ry="22"
            fill="url(#daisyPetal)"
            stroke="#ca8a04"
            strokeWidth="0.8"
            transform={`rotate(${angle} 70 70)`}
          />
        );
      })}

      {/* Warm Orange-Gold Center */}
      <circle cx="70" cy="70" r="17" fill="#ea580c" />
      <circle cx="70" cy="70" r="14" fill="#f59e0b" />
      <circle cx="70" cy="70" r="9" fill="#fef08a" opacity="0.6" />
    </g>
  </svg>
);

// 5. Delicate Cherry Blossom / Sakura Flower (Top-Right / Accents)
const CherryBlossom = ({ className }) => (
  <svg viewBox="0 0 120 120" className={className} fill="none">
    <defs>
      <linearGradient id="sakuraGrad" x1="0%" y1="0%" x2="100%" y2="100%">
        <stop offset="0%" stopColor="#fce7f3" />
        <stop offset="60%" stopColor="#f472b6" />
        <stop offset="100%" stopColor="#db2777" />
      </linearGradient>
      <filter id="sakuraShadow" x="-15%" y="-15%" width="130%" height="130%">
        <feDropShadow dx="0" dy="5" stdDeviation="4" floodOpacity="0.12" />
      </filter>
    </defs>

    <g filter="url(#sakuraShadow)">
      {Array.from({ length: 5 }).map((_, i) => {
        const angle = i * 72;
        return (
          <path
            key={i}
            d="M60 60 C50 35 45 22 55 18 C65 22 62 35 60 60 Z"
            fill="url(#sakuraGrad)"
            stroke="#be185d"
            strokeWidth="0.7"
            transform={`rotate(${angle} 60 60)`}
          />
        );
      })}
      <circle cx="60" cy="60" r="6" fill="#fef08a" />
      <circle cx="60" cy="60" r="2.5" fill="#e11d48" />
    </g>
  </svg>
);

const FloatingFlowers = () => {
  return (
    <div className="pointer-events-none fixed inset-0 overflow-hidden z-0 select-none">
      {/* 1. Pink Lotus on Lily Pad - Floating Mid-Left (Matching Reference Photo) */}
      <div className="absolute left-[5%] sm:left-[10%] md:left-[14%] top-[34%] animate-float-1">
        <LotusFlower className="w-24 h-24 sm:w-32 sm:h-32 md:w-36 md:h-36 transition-transform hover:scale-105" />
      </div>

      {/* 2. Vibrant Hibiscus with Leaves - Floating Bottom-Left (Matching Reference Photo) */}
      <div className="absolute left-[8%] sm:left-[14%] md:left-[19%] top-[68%] sm:top-[70%] animate-float-2">
        <HibiscusFlower className="w-24 h-24 sm:w-32 sm:h-32 md:w-36 md:h-36 transition-transform hover:scale-105" />
      </div>

      {/* 3. Golden Sunflower with Leaf - Floating Mid-Right (Matching Reference Photo) */}
      <div className="absolute right-[5%] sm:right-[10%] md:right-[13%] top-[30%] sm:top-[32%] animate-float-3">
        <Sunflower className="w-24 h-24 sm:w-32 sm:h-32 md:w-36 md:h-36 transition-transform hover:scale-105" />
      </div>

      {/* 4. Yellow Blossom / Daisy - Floating Bottom-Right (Matching Reference Photo) */}
      <div className="absolute right-[8%] sm:right-[15%] md:right-[20%] top-[64%] sm:top-[66%] animate-float-4">
        <YellowBlossom className="w-20 h-20 sm:w-28 sm:h-28 md:w-32 md:h-32 transition-transform hover:scale-105" />
      </div>

      {/* 5. Subtle Cherry Blossom Accent - Floating Top-Right */}
      <div className="absolute right-[22%] sm:right-[26%] top-[14%] animate-float-1 opacity-80">
        <CherryBlossom className="w-14 h-14 sm:w-18 sm:h-18" />
      </div>

      {/* 6. Subtle Pink Petal Accent - Floating Top-Left */}
      <div className="absolute left-[20%] sm:left-[24%] top-[16%] animate-float-3 opacity-80">
        <CherryBlossom className="w-12 h-12 sm:w-16 sm:h-16" />
      </div>
    </div>
  );
};

export default FloatingFlowers;
