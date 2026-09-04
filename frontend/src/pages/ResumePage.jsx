import React, { useState, useEffect } from 'react';
import { resumeApi } from '../api/resumeApi';
import { ResumeBuilder } from '../components/resume/ResumeBuilder';
import { FileText, Sparkles } from 'lucide-react';
import { MOCK_RESUME } from '../data/mockData';

export const ResumePage = () => {
  const [resume, setResume] = useState(null);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    const fetchResume = async () => {
      setLoading(true);
      try {
        const resumes = await resumeApi.getMyResumes();
        setResume(resumes[0] || MOCK_RESUME);
      } catch (err) {
        setResume(MOCK_RESUME);
      } finally {
        setLoading(false);
      }
    };
    fetchResume();
  }, []);

  return (
    <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 py-8 space-y-6">
      {/* Header Banner */}
      <div className="bg-gradient-to-r from-slate-900 via-indigo-950 to-slate-900 rounded-3xl p-8 sm:p-10 text-white shadow-xl flex flex-col md:flex-row items-center justify-between gap-6">
        <div className="space-y-2 max-w-xl">
          <div className="inline-flex items-center gap-1.5 px-3 py-1 rounded-full text-xs font-semibold bg-indigo-500/20 text-indigo-300 border border-indigo-500/30">
            <Sparkles className="w-3.5 h-3.5" />
            <span>Interactive ATS-Friendly Resume Builder</span>
          </div>
          <h1 className="text-3xl font-extrabold tracking-tight">
            Profile & Resume Builder
          </h1>
          <p className="text-xs sm:text-sm text-slate-300 leading-relaxed">
            Customize your professional portfolio, work experiences, technical proficiencies, and degrees. Automatically shared when applying to jobs on CareerHub.
          </p>
        </div>
      </div>

      {loading ? (
        <div className="h-96 rounded-3xl bg-slate-100 animate-pulse" />
      ) : (
        <ResumeBuilder
          initialResume={resume}
          onSave={(updated) => setResume(updated)}
        />
      )}
    </div>
  );
};
